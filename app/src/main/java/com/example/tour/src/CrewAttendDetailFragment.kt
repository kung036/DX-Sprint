package com.example.tour.src

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.tour.R
import com.example.tour.config.BaseFragment
import com.example.tour.databinding.*
import com.example.tour.src.test.model.CrewFestival

private lateinit var binding: FragmentCrewAttendDetailBinding

class CrewAttendDetailFragment : BaseFragment<FragmentCrewAttendDetailBinding>
(FragmentCrewAttendDetailBinding::bind, R.layout.fragment_crew_attend_detail){
    private val dataSet = arrayListOf<CrewFestival>()
    private var image_url:String? = null
    private var title:String? = null
    private var place:String? = null
    private var festival_id:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Apator 내용 받아오기
        arguments?.let {
            image_url = it.getString("image_url")
            title = it.getString("title")
            place = it.getString("place")
            festival_id = it.getInt("festival_id")
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

        binding.festivalName.text = title
    }


}