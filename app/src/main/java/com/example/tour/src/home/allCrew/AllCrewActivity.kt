package com.example.tour.src.home.allCrew

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tour.R
import com.example.tour.config.BaseActivity
import com.example.tour.config.BaseFragment
import com.example.tour.databinding.ActivityAllCrewBinding
import com.example.tour.databinding.ActivityCrewAttendDetailBinding
import com.example.tour.src.home.MainActivity
import com.example.tour.databinding.FragmentMainBinding
import com.example.tour.databinding.ItemRecycleMainBinding
import com.example.tour.src.crew.CrewAdapter
import com.example.tour.src.crew.MainCrewFragmentService
import com.example.tour.src.crew.PopularCrew
import com.example.tour.src.crew.detail.CrewAttendDetailActivityInterface
import com.example.tour.src.crew.make.CrewMakeActivity
import com.example.tour.src.home.collect.FestivalCollectActivity
import com.example.tour.src.home.detail.DetailActivity
import com.example.tour.src.home.model.GetCrewRes
import com.example.tour.src.home.model.GetFestivalResponse
import java.net.URL
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

var Test:JSONArray? = null

data class FewCrew(
    val crewIdx: Int, val festivalIdx: Int, val festivalImageUrl: String, val title: String, val crewName: String,
    val crewGender: String, val crewHeadCount: Int, val totalHeadCount: Int, val crewMeetDate: String,
    val dibsCount: String)

class AllCrewActivity : BaseActivity<ActivityAllCrewBinding>(
    ActivityAllCrewBinding::inflate),
    MainFragmentInterface {
    private val dataSet = arrayListOf<FewCrew>()
    private lateinit var rvAdapter: MainAdapter
    private var image_url = arrayListOf<String>()
    private var title = arrayListOf<String>()
    private var place = arrayListOf<String>()
    private var content = arrayListOf<String>()
    private var date = arrayListOf<String>()
    private var address = arrayListOf<String>()
    private var money = arrayListOf<String>()
    private var phoneNumber = arrayListOf<String>()
    private var homepageURL = arrayListOf<String>()
    private var facility = arrayListOf<String>()
    private var festivalIdx = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 리사이클러 뷰
        rvAdapter = MainAdapter(dataSet, this)
        binding.mainViewCrews.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.mainViewCrews.adapter = rvAdapter

        MainFragmentService(this).tryGetFewCrews()
    }

    override fun onGetFewCrewSuccess(response: GetCrewRes) {
        var size = response.result.size
        when (response.message) {
            "요청에 성공하였습니다." -> {
                for (i in 0 until size){
                    dataSet.add(
                        FewCrew(response.result[i].crewIdx,response.result[i].festivalIdx, response.result[i].festivalImageUrl,
                            response.result[i].title, response.result[i].crewName, response.result[i].crewGender,
                            response.result[i].crewHeadCount,response.result[i].totalHeadCount, response.result[i].crewMeetDate,
                            response.result[i].dibsCount)
                    )
                }
                rvAdapter.notifyDataSetChanged()
                rvAdapter = MainAdapter(dataSet, this)
                binding.mainViewCrews.adapter = rvAdapter
            }
            else -> {
                Toast.makeText(this, response.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onGetFewCrewFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}