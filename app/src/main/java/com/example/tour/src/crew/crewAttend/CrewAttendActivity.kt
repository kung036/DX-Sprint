package com.example.tour.src.crew.crewAttend

import android.graphics.Bitmap
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tour.config.BaseActivity
import android.widget.Toast
import com.example.tour.databinding.*
import com.example.tour.util.ImageURLClass
import com.example.tour.src.crew.crewAttend.model.GetCrewFestivalRes
import java.net.URL

data class CrewFestival(val crewIdx: Int, val festivalIdx: Int, val festivalImageUrl: String, val title: String, val crewName: String,
                        val crewGender: String, val crewHeadCount: Int, val totalHeadCount: Int, val crewMeetDate: String,
                        val dibsCount: String, val place: String?)
class CrewAttendActivity : BaseActivity<ActivityCrewAttendBinding>
        (ActivityCrewAttendBinding::inflate), CrewAttendActivityInterface {
    private lateinit var CAdapter: CrewApater_me
    private val dataSet = arrayListOf<CrewFestival>()
    private var image_url:String? = null
    private var title:String? = null
    private var place:String? = null
    private var festivalIdx:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Apator 내용 받아오기
        var intent = intent
        image_url = intent.getStringExtra("image_url")
        title = intent.getStringExtra("title")
        place = intent.getStringExtra("place")
        festivalIdx = intent.getIntExtra("festivalIdx",0)

        // 이미지 URL을 Bitmap으로 변경
        var image_task: ImageURLClass = ImageURLClass()
        image_task = ImageURLClass().apply {
            url = URL(image_url)
        }
        var bitmap: Bitmap = image_task.execute().get()
        binding.crewAttendImage.setImageBitmap(bitmap) // String 이미지
        binding.crewAttendTitle.text = title
        binding.crewAttendPlace.text = place

        CrewAttendActivityService(this).tryGetCrewFestivalDetail(festivalIdx)

        // 리사이클러 뷰
        CAdapter = CrewApater_me(dataSet, this)
        binding.crewAttendRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.crewAttendRecycler.adapter = CAdapter

    }

    override fun onGetCrewByFestivalSuccess(response: GetCrewFestivalRes) {
        var size = response.result.size
        when (response.message) {
            "요청에 성공하였습니다." -> {
                for (i in 0 until size){
                    dataSet.add(CrewFestival(response.result[i].crewIdx,response.result[i].festivalIdx, response.result[i].festivalImageUrl,
                        response.result[i].title, response.result[i].crewName, response.result[i].crewGender,
                        response.result[i].crewHeadCount,response.result[i].totalHeadCount, response.result[i].crewMeetDate,
                        response.result[i].dibsCount, place))
                }
                CAdapter.notifyDataSetChanged()
                CAdapter = CrewApater_me(dataSet, this)
            }
            else -> {
                Toast.makeText(this, response.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onGetCrewByFestivalFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}