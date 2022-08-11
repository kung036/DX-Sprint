package com.example.tour.src.test.model

import android.content.Context
import android.graphics.Bitmap
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

private lateinit var binding: FragmentCrewAttendBinding

class CrewAttendFragment : Fragment() {
    private lateinit var CAdapter: CrewApater_me
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
        binding = FragmentCrewAttendBinding.inflate(inflater, container, false)

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
        binding.crewAttendImage.setImageBitmap(bitmap) // String 이미지
        binding.crewAttendTitle.text = title
        binding.crewAttendPlace.text = place

        // 리사이클러 뷰
        addData() // 데이터추가
        CAdapter = CrewApater_me(dataSet, requireContext(), mainActivity)
//        rvAdapter = MyAdapter(dataSet)
        binding.crewAttendRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.crewAttendRecycler.adapter = CAdapter
//        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
//            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT
//        ) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                val fromPos: Int = viewHolder.adapterPosition
//                val toPos: Int = target.adapterPosition
//                return true
//            }
//        }
//        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.crewAttendRecycler)

    }
    // 리사이클러 뷰 데이터 추가
    fun addData() {
        dataSet.add(
            CardClass(
                image_url,
                null, //day
                title,
                place,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                festival_id,

                null
            )
        )
    }}