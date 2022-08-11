package com.example.tour.src

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tour.src.MainActivity
import com.example.tour.databinding.*
import java.net.URL
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

private lateinit var binding: FragmentCrewAttendDetailBinding

class CrewAttendDetailFragment : Fragment() {
    private val dataSet = arrayListOf<CardClass>()
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCrewAttendDetailBinding.inflate(inflater, container, false)

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

        binding.festivalName.text = title
    }
}