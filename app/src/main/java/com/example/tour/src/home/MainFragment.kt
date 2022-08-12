package com.example.tour.src.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tour.src.home.MainActivity
import com.example.tour.databinding.FragmentMainBinding
import com.example.tour.databinding.ItemRecycleMainBinding
import java.net.URL
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

private lateinit var binding: FragmentMainBinding
private lateinit var binding2: ItemRecycleMainBinding
var Test:JSONArray? = null


class MainFragment : Fragment() {
    //class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::bind,
//    R.layout.fragment_main){
    private val dataSet = arrayListOf<CardClass>()
    private lateinit var DtAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding2 = ItemRecycleMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    // fragment에서 runOnithread 사용하기
    lateinit var mainActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 리사이클러 뷰
        addData() // 데이터추가

        DtAdapter = MainAdapter(dataSet, requireContext(), mainActivity)
        binding.mainViewFestival.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.mainViewFestival.adapter = DtAdapter

    }
    // 네트워크를 이용할 때는 쓰레드를 사용해서 접근해야 함(JSON 가져오기)
    inner class NetworkThread: Thread() {
        override fun run() {
            // 접속할 페이지 주소: Site
            var base_url = "http://apis.data.go.kr/6260000/FestivalService/getFestivalKr?"
            var key = "serviceKey=iOsSg7SsQnn9oHGqbJo2A73DilcpwmIyKVrnq0puly4WPZgmww7UzNhpFisZn32fvFS2dCXuXE9kiu9I4L0kgg%3D%3D"
            var and = "&"
            var resultType = "resultType=json"
            var count = "numOfRows=30"
            var site = base_url + key + and + resultType + and + count
            var url = URL(site)
            var connect = url.openConnection()
            var input = connect.getInputStream()
            var isr = InputStreamReader(input)

            // br: 라인 단위로 데이터를 읽어오기 위해서 만듦
            var br = BufferedReader(isr)

            // Json 문서는 일단 문자열로 데이터를 모두 읽어온 후, Json에 관련된 객체를 만들어서 데이터를 가져옴
            var str: String? = null
            var buf = StringBuffer()

            do {
                str = br.readLine()

                if (str != null) {
                    buf.append(str)
                }
            } while (str != null)

            // 전체가 객체로 묶여있기 때문에 객체형태로 가져옴
            var root = JSONObject(buf.toString())
            var festival = root.getJSONObject("getFestivalKr")
            var items = festival.getJSONArray("item")
            Test = items
            Log.d("shin", "what : $Test")
            var itemSize = festival.getInt("numOfRows")
            Log.d("shin", "itemSize : $itemSize\n\n")

            // 화면에 출력
            for (i in 0 until itemSize) {
                var obj = items.getJSONObject(i)
                var image: String? = obj.getString("MAIN_IMG_NORMAL") // 이미지 URL
                var day: String? = obj.getString("USAGE_DAY") // 운영기간
                var title: String? = obj.getString("TITLE") // 축제 제목
                var place: String? = obj.getString("MAIN_PLACE") // 장소

                var address:String? = obj.getString("ADDR1") // 주소
                var phoneNumber:String? = obj.getString("CNTCT_TEL") // 연락처
                var homepageURL:String? = obj.getString("HOMEPAGE_URL") // 홈페이지
                var traffic:String? = obj.getString("TRFC_INFO") // 교통정보
                var time:String? = obj.getString("USAGE_DAY_WEEK_AND_TIME") // 이용요일 및 시간
                var money:String? = obj.getString("USAGE_AMOUNT") // 이용요금
                var content:String? = obj.getString("ITEMCNTNTS").trim().replace("\n\n","\n") // 상세내용
                var facility:String? = obj.getString("MIDDLE_SIZE_RM1") // 편의시설
                var festival_id:Int = obj.getInt("UC_SEQ")// festival ID
                Log.d("shin", "데이터 받아오기(MainFragment) : $festival_id")

                if(day == "") day = obj.getString("USAGE_DAY_WEEK_AND_TIME") // 이용요일 및 시간

                dataSet.add(
                    CardClass(
                        image,
                        day,
                        title,
                        place,

                        address,
                        phoneNumber,
                        homepageURL,
                        traffic,
                        time,
                        money,
                        content,
                        facility,
                        festival_id,

                        obj
                    )
                )
            }
        }
    }

    // 리사이클러 뷰 데이터 추가
    fun addData() {
        var thread = NetworkThread() // 쓰레드 생성(JSON)
        thread.start() // 쓰레드 동작(JSON)
        thread.join()
    }

}