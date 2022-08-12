package com.example.tour.src.crew.make

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tour.src.home.MainActivity
import com.example.tour.R
import com.example.tour.databinding.*
import com.example.tour.src.home.CardClass
import com.example.tour.src.home.MainAdapter

private lateinit var binding: FragmentCrewMakeBinding

class CrewMakeFragment : Fragment() {
    //class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::bind,
//    R.layout.fragment_main){
    private val dataSet = arrayListOf<CardClass>()
    private lateinit var rvAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCrewMakeBinding.inflate(inflater, container, false)

        binding.crewButtonMake.setOnClickListener {
            mainActivity.supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, CrewMakeFinishFragment()).commit()
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
    }
}