package com.example.tour.src

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tour.databinding.FragmentTestBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class TestFragment : Fragment() {
    private lateinit var binding: FragmentTestBinding
//class TestFragment : BaseFragment<FragmentTestBinding>(
//    FragmentTestBinding::bind, R.layout.fragment_test) {

    private var title:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Apator 내용 받아오기
        arguments?.let {
            title = it.getString("title")
        }
        Log.d("shin", "데이터 받아오기 : $title")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTestBinding.inflate(inflater, container, false)

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

        binding.buttonDateDialog.setOnClickListener {
            Toast.makeText(context, "test ...", Toast.LENGTH_SHORT).show()
        }

        // 데이터 초기화
        binding.textviewResponse.text = ""

        // fragment에서 runOnithread 사용하기


        // 버튼을 누르면 쓰레드 동작
        binding.buttonGet.setOnClickListener {
            Toast.makeText(context, "API test ...", Toast.LENGTH_SHORT).show()

            // 쓰레드 생성
            var thread = NetworkThread()
            thread.start()
        }

    }

    // 네트워크를 이용할 때는 쓰레드를 사용해서 접근해야 함
    inner class NetworkThread: Thread(){
        override fun run() {
            // 접속할 페이지 주소: Site
            var base_url = "http://apis.data.go.kr/6260000/FestivalService/getFestivalKr?"
            var key = "serviceKey=iOsSg7SsQnn9oHGqbJo2A73DilcpwmIyKVrnq0puly4WPZgmww7UzNhpFisZn32fvFS2dCXuXE9kiu9I4L0kgg%3D%3D"
            var and = "&"
            var resultType = "resultType=json"
            var site = base_url + key + and + resultType
            var url = URL(site)
            var connect = url.openConnection()
            var input = connect.getInputStream()
            var isr = InputStreamReader(input)
            // br: 라인 단위로 데이터를 읽어오기 위해서 만듦
            var br = BufferedReader(isr)

            // Json 문서는 일단 문자열로 데이터를 모두 읽어온 후, Json에 관련된 객체를 만들어서 데이터를 가져옴
            var str: String? = null
            var buf = StringBuffer()

            do{
                str = br.readLine()

                if(str!=null){
                    buf.append(str)
                }
            }while (str!=null)

            // 전체가 객체로 묶여있기 때문에 객체형태로 가져옴
            var root = JSONObject(buf.toString())

            var festival = root.getJSONObject("getFestivalKr")
            var items = festival.getJSONArray("item")
            var itemSize = festival.getInt("numOfRows")

            // 화면에 출력
            mainActivity.runOnUiThread {
                for(i in 0 until itemSize) {
                    var obj = items.getJSONObject(i)

                    var title:String? = obj.getString("TITLE") // 축제 제목
                    var place:String? = obj.getString("MAIN_PLACE") // 장소
                    var address:String? = obj.getString("ADDR1") // 주소
                    var phoneNumber:String? = obj.getString("CNTCT_TEL") // 연락처
                    var homepageURL:String? = obj.getString("HOMEPAGE_URL") // 홈페이지
                    var traffic:String? = obj.getString("TRFC_INFO") // 교통정보
                    var day:String? = obj.getString("USAGE_DAY") // 운영기간
                    var time:String? = obj.getString("USAGE_DAY_WEEK_AND_TIME") // 이용요일 및 시간
                    var money:String? = obj.getString("USAGE_AMOUNT") // 이용요금
                    var image:String? = obj.getString("MAIN_IMG_NORMAL") // 이미지 URL
                    var content:String? = obj.getString("ITEMCNTNTS").trim().replace("\n\n","\n") // 상세내용
                    var facility:String? = obj.getString("MIDDLE_SIZE_RM1") // 편의시설

                    // 데이터 추가하기
                    binding.textviewResponse.append("$i >> title : $title\n" +
                            "place : $place\n" +
                            "address : $address\n" +
                            "phoneNumber : $phoneNumber\n" +
                            "homepageURL : $homepageURL\n" +
                            "traffic : $traffic\n" +
                            "day : $day\n" +
                            "time : $time\n" +
                            "money : $money\n" +
                            "image : $image\n" +
                            "content : $content\n" +
                            "facility : $facility\n\n")

                }

            }
        }
    }
}