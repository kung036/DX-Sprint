package com.example.tour.src.crew

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tour.src.crew.model.GetCrewRes
import com.example.tour.config.BaseFragment
import com.example.tour.databinding.FragmentMainCrewEditBinding
import com.example.tour.R
import com.example.tour.src.home.MainActivity

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
            //val intent = Intent(context,DetailCreateCrewActivity::class.java)
            //context?.startActivity(intent)
        }
    }

    override fun onGetCrewSuccess(response: GetCrewRes) {
        var size = response.result.size
        when (response.message) {
            "요청에 성공하였습니다." -> {
                for (i in 0 until size){
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
                showCustomToast(response.message.toString())
            }
        }
    }

    override fun onGetCrewFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}