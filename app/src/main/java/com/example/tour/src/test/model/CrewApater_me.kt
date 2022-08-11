package com.example.tour.src.test.model


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.example.tour.R
import com.example.tour.databinding.ActivityMainBinding
import com.example.tour.databinding.FragmentRecycleCrewBinding
//import com.example.tour.databinding.ItemHeaderBinding
import com.example.tour.databinding.FragmentRecycleMainBinding
import com.example.tour.src.CardClass
import com.example.tour.src.CrewAttendDetailFragment
import com.example.tour.src.ImageURLClass
import com.google.android.material.snackbar.Snackbar
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

//import com.example.tour.src.MyAdapter.HeaderViewHolder

private lateinit var binding: FragmentRecycleCrewBinding

class CrewApater_me(
    private val dataSet: ArrayList<CardClass>,
    private val context: Context,
    private var mainActivity: MainActivity
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            FragmentRecycleCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
//            is HeaderViewHolder -> holder.bind()
            is ViewHolder -> holder.bind(dataSet[position])
        }
//        holder.bind(dataSet[position])

    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(private val binding: FragmentRecycleCrewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CardClass) {
            // 이미지 URL을 Bitmap으로 변경
            var image_task: ImageURLClass = ImageURLClass()
            image_task = ImageURLClass().apply {
                url = URL(data.image)
            }
            var bitmap: Bitmap = image_task.execute().get()

            binding.crewViewImage.setImageBitmap(bitmap) // String 이미지
            binding.festivalTitle.text = data.title

            // 터치했을 때
            binding.crewViewImage.setOnClickListener {
                // 데이터 전달
                val bundle = Bundle()
                var crewAttendDetailFragment: Fragment = CrewAttendDetailFragment()
                bundle.putString("image_url", data.image)
                bundle.putString("title", data.title)
                bundle.putString("place", data.place)
                bundle.putInt("festival_id", data.festival_id)
                bundle.putInt("crewIdx", data.crewIdx)
                crewAttendDetailFragment.arguments = bundle

                // 화면 전환
                mainActivity.supportFragmentManager.beginTransaction()
                    .replace(R.id.framelaout_container, crewAttendDetailFragment).commit()
            }
        }
    }
}