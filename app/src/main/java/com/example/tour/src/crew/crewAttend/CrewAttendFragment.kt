package com.example.tour.src.crew.crewAttend.model

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tour.R
import com.example.tour.config.BaseFragment
import com.example.tour.src.home.MainActivity
import com.example.tour.databinding.*
import com.example.tour.util.ImageURLClass
import com.example.tour.src.crew.crewAttend.CrewAttendFragmentInterface
import com.example.tour.src.crew.crewAttend.CrewAttendFragmentService
import java.net.URL

private lateinit var binding: FragmentCrewAttendBinding
data class CrewFestival(val crewIdx: Int, val festivalIdx: Int, val festivalImageUrl: String, val title: String, val crewName: String,
                        val crewGender: String, val crewHeadCount: Int, val totalHeadCount: Int, val crewMeetDate: String,
                        val dibsCount: String)
class CrewAttendFragment : BaseFragment<FragmentCrewAttendBinding>
    (FragmentCrewAttendBinding::bind, R.layout.fragment_crew_attend),
    CrewAttendFragmentInterface {
    private lateinit var CAdapter: CrewApater_me
    private val dataSet = arrayListOf<CrewFestival>()
    private var image_url:String? = null
    private var title:String? = null
    private var place:String? = null
    private var festivalIdx:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Apator 내용 받아오기
        arguments?.let {
            image_url = it.getString("image_url")
            title = it.getString("title")
            place = it.getString("place")
            festivalIdx = it.getInt("festivalIdx")
        }
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
        binding.crewAttendImage.setImageBitmap(bitmap) // String 이미지
        binding.crewAttendTitle.text = title
        binding.crewAttendPlace.text = place

        CrewAttendFragmentService(this).tryGetCrewFestivalDetail(festivalIdx)

        // 리사이클러 뷰
        CAdapter = CrewApater_me(dataSet, requireContext(), mainActivity)
        binding.crewAttendRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
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
                        response.result[i].dibsCount))
                }
                CAdapter.notifyDataSetChanged()
                CAdapter = CrewApater_me(dataSet, requireContext(), activity as MainActivity)
            }
            else -> {
                showCustomToast(response.message.toString())
            }
        }
    }

    override fun onGetCrewByFestivalFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}