package com.example.tour.src

import android.content.Context
import android.graphics.*
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tour.R
import com.example.tour.config.BaseFragment
import com.example.tour.databinding.ActivityMainBinding
import com.example.tour.databinding.FragmentMainBinding
import com.example.tour.databinding.FragmentRecycleMainBinding
import com.example.tour.databinding.FragmentTestBinding
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import com.example.tour.src.ImageURLClass
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

//data class card (val main_view_image: Int, val main_view_date: String, val main_view_title: String, val main_view_place: String)
//data class card (val main_view_image: Bitmap?, val main_view_date: String?, val main_view_title: String?, val main_view_place: String?)
data class card (val main_view_image: String?, val main_view_date: String?, val main_view_title: String?, val main_view_place: String?)

private lateinit var binding: FragmentMainBinding
private lateinit var binding2: FragmentRecycleMainBinding
var Test:JSONArray? = null


class MainFragment : Fragment() {
//class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::bind,
//    R.layout.fragment_main){
    private val dataSet = arrayListOf<card>()
    private lateinit var rvAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding2 = FragmentRecycleMainBinding.inflate(inflater, container, false)

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
        addData() // 데이터추가(잠시 주석)

        rvAdapter = MyAdapter(dataSet)
        binding.mainViewFestival.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.mainViewFestival.adapter = rvAdapter
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT
        ){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPos: Int = viewHolder.adapterPosition
                val toPos: Int = target.adapterPosition
                rvAdapter.swapData(fromPos, toPos)
                return true
            }

            //스와이프시 데이터 삭제
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                rvAdapter.removeData(viewHolder.layoutPosition)
            }
        }
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.mainViewFestival)
    }

    // 네트워크를 이용할 때는 쓰레드를 사용해서 접근해야 함(JSON 가져오기)
    inner class NetworkThread: Thread() {
        var key2:String = ""

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
//            mainActivity.runOnUiThread {
                for (i in 0 until itemSize) {
                    var obj = items.getJSONObject(i)
                    var image: String? = obj.getString("MAIN_IMG_NORMAL") // 이미지 URL
                    var day: String? = obj.getString("USAGE_DAY") // 운영기간
                    var title: String? = obj.getString("TITLE") // 축제 제목
                    var place: String? = obj.getString("MAIN_PLACE") // 장소
                    if(day == "") day = obj.getString("USAGE_DAY_WEEK_AND_TIME") // 이용요일 및 시간
//                    Log.d("shin", "image : $image\n" +
//                            "day : $day\n" +
//                            "title : $title\n" +
//                            "place : $place\n")

                    dataSet.add(
                        card(
                            image,
                            day,
                            title,
                            place+" !!what"
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
        Log.d("shin", "what : $Test")
    }
}