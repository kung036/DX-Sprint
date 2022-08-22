package com.example.tour.src.crew

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tour.src.crew.model.GetCrewRes
import com.example.tour.config.BaseFragment
import com.example.tour.databinding.FragmentMainCrewEditBinding
import com.example.tour.R
import com.example.tour.config.ApplicationClass.Companion.NICKNAME_TOKEN
import com.example.tour.config.ApplicationClass.Companion.sSharedPreferences
import com.example.tour.src.crew.make.CrewMakeActivity
import com.example.tour.src.home.MainActivity
import com.example.tour.src.home.allCrew.AllCrewActivity

data class PopularCrew(
    val crewIdx: Int, val festivalIdx: Int, val festivalImageUrl: String, val title: String, val crewName: String,
    val crewGender: String, val crewHeadCount: Int, val totalHeadCount: Int, val crewMeetDate: String,
    val dibsCount: String)

class MainCrewFragment : BaseFragment<FragmentMainCrewEditBinding>
    (FragmentMainCrewEditBinding::bind, R.layout.fragment_main_crew_edit),
    MainCrewFragmentInterface {
    private lateinit var rvAdapter : CrewAdapter
    private var data = arrayListOf<PopularCrew>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var a : Int? = null
        //showLoadingDialog(requireContext())
        MainCrewFragmentService(this).tryGetCrews()

        rvAdapter = CrewAdapter(data, requireContext(),activity as MainActivity)
        binding.popularCrew.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        binding.popularCrew.adapter = rvAdapter

        binding.createCrew.setOnClickListener {
            var nickName = sSharedPreferences.getString(NICKNAME_TOKEN, "EMPTY")
            if (nickName == "EMPTY") {
                Toast.makeText(context, "로그인이 필요한 기능입니다.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(context, CrewMakeActivity::class.java)
                intent.putExtra("title", "")
                startActivity(intent)
            }
        }
        binding.participateCrew.setOnClickListener {
            val intent = Intent(context, AllCrewActivity::class.java)
            startActivity(intent)
        }    }

    override fun onGetCrewSuccess(response: GetCrewRes) {
        var size = response.result.size
        when (response.message) {
            "요청에 성공하였습니다." -> {
                for (i in 0 until 3){
                    data.add(
                        PopularCrew(response.result[i].crewIdx,response.result[i].festivalIdx, response.result[i].festivalImageUrl,
                        response.result[i].title, response.result[i].crewName, response.result[i].crewGender,
                        response.result[i].crewHeadCount,response.result[i].totalHeadCount, response.result[i].crewMeetDate,
                        response.result[i].dibsCount)
                    )
                }
                rvAdapter.notifyDataSetChanged()
                rvAdapter = CrewAdapter(data, requireContext(),activity as MainActivity)
            }
            else -> {
                Toast.makeText(context, response.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onGetCrewFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}