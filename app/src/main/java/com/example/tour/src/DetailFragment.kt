package com.example.tour.src

import CrewMakeFragment
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.tour.src.MainActivity
import com.example.tour.R
import com.example.tour.config.BaseFragment
import com.example.tour.databinding.FragmentDetailBinding
import com.example.tour.databinding.FragmentMainBinding
import com.example.tour.databinding.FragmentTestBinding
import com.example.tour.src.test.model.CrewAttendFragment
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private var image_url:String? = null
    private var title:String? = null
    private var place:String? = null
    private var content:String? = null
    private var date:String? = null
    private var address:String? = null
    private var money:String? = null
    private var phoneNumber:String? = null
    private var homepageURL:String? = null
    private var facility:String? = null
    private var festival_id:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Apator 내용 받아오기
        arguments?.let {
            image_url = it.getString("image_url")
            title = it.getString("title")
            place = it.getString("place")
            content = it.getString("content")
            date = it.getString("date")
            address = it.getString("address")
            money = it.getString("money")
            phoneNumber = it.getString("phoneNumber")
            homepageURL = it.getString("homepageURL")
            facility = it.getString("facility")
            festival_id = it.getInt("festival_id")
//            Log.d("shin", "데이터 받아오기(DetailFragment) : $festival_id")
        }
//        Log.d("shin", "데이터 받아오기 : $image_url")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        // 크루 만들기
        binding.imageFloatingCrewMake.setOnClickListener {
            mainActivity.supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, CrewMakeFragment()).commit()
        }
        // 크루 참석하기
        binding.imageFloatingCrewAttend.setOnClickListener {
            // 데이터 전달
            val bundle = Bundle()
            var CrewAttendFagment:Fragment = CrewAttendFragment()
            bundle.putString("image_url", image_url)
            bundle.putString("title", title)
            bundle.putString("place", place)
            bundle.putInt("festivalIdx", festival_id)
            CrewAttendFagment.arguments = bundle

            mainActivity.supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, CrewAttendFagment).commit()
        }

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

        // 이미지 URL을 Bitmap으로 변경
        var image_task: ImageURLClass = ImageURLClass()
        image_task = ImageURLClass().apply {
            url = URL(image_url)
        }
        var bitmap: Bitmap = image_task.execute().get()

        // 선택한 카드 데이터 넣기
        binding.detailImageVaner.setImageBitmap(bitmap)
        binding.detailTextTitle.text = title
        binding.detailTextPlace.text = place
        binding.detailTextContent.text = content
        binding.detailTextDate.text = date
        binding.detailTextAddress.text = address
        binding.detailTextMoney.text = money
        binding.detailTextPhonenumber.text = phoneNumber
        binding.detailTextHomepageURL.text = homepageURL
        binding.detailTextFacility.text = facility
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
//            var address: String = root.getString("address")
//            var count: Int = root.getInt("count")

//            var festival = root.getJSONArray("getFestivalKr")
            var festival = root.getJSONObject("getFestivalKr")
            var items = festival.getJSONArray("item")
            var itemSize = festival.getInt("numOfRows")

            // 화면에 출력
//            runOnUiThread {
            mainActivity.runOnUiThread {
                // test
//                binding.textviewResponse.append("test : ${root}\n")
//                binding.textviewResponse.append("test : ${items}\n")
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
//                    binding.textviewResponse.append("$i >> title : $title\n" +
//                            "place : $place\n" +
//                            "address : $address\n" +
//                            "phoneNumber : $phoneNumber\n" +
//                            "homepageURL : $homepageURL\n" +
//                            "traffic : $traffic\n" +
//                            "day : $day\n" +
//                            "time : $time\n" +
//                            "money : $money\n" +
//                            "image : $image\n" +
//                            "content : $content\n" +
//                            "facility : $facility\n\n")

                }

//                binding.textviewResponse.append("address: ${address}\n")
//                binding.textviewResponse.append("count: ${count}\n\n\n")

                // 객체 안에 있는 stores라는 이름의 리스트를 가져옴
//                var stores = root.getJSONArray("stores")
//
//                // 리스트에 있는 데이터 중 100개만 읽어옴
//                for(i in 0 until 100){
//                    var obj2 = stores.getJSONObject(i)
//
//                    var addr: String = obj2.getString("addr")
//                    var name: String = obj2.getString("name")
//                    var remain_stat: String = obj2.getString("remain_stat")
//                    var detail: String? = null
//
//                    if(remain_stat.contentEquals("plenty")){
//                        detail = "100개 이상"
//                    }else if(remain_stat.contentEquals("some")){
//                        detail = "30개 이상 100개 미만"
//                    }else if(remain_stat.contentEquals("few")){
//                        detail = "2개 이상 30개 미만"
//                    }else if(remain_stat.contentEquals("empty")){
//                        detail = "1개 이하"
//                    }else if(remain_stat.contentEquals("break")){
//                        detail = "판매 중지"
//                    }

                // 화면에 출력
//                    runOnUiThread {
//                    mainActivity.runOnUiThread {
//                        binding.textviewResponse.append("판매처 주소: ${addr}\n")
//                        binding.textviewResponse.append("판매처 이름: ${name}\n")
//                        binding.textviewResponse.append("재고 상태: ${remain_stat} = $detail\n\n")
//                    }
//                }
            }
        }
    }
}