package com.example.tour.crew

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tour.R
import com.example.tour.databinding.FragmentRecycleCrewBinding
import com.example.tour.detail.CrewDetailFragment
import com.example.tour.src.MainActivity
import com.example.tour.src.CrewAttendDetailFragment

private lateinit var binding : FragmentRecycleCrewBinding
class CrewAdapter(private val dataSet: ArrayList<PopularCrew>, var con : Context, private var mainActivity: MainActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1
    private val TYPE_FOOTER = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = FragmentRecycleCrewBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder -> holder.bind(dataSet[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(private val binding : FragmentRecycleCrewBinding) : RecyclerView.ViewHolder(binding.root) {
        //var textData = arrayListOf<CrewParticipate>()
        fun bind(data: PopularCrew) {
            Glide.with(con).load(data.festivalImageUrl).into(binding.crewViewImage);
            var titleStr = data.title.split("(")
            binding.festivalTitle.text = titleStr[0].toString()
            binding.crewName.text = data.crewName
            binding.crewCondition.text = data.crewGender
            binding.crewHeadCount.text = "${data.crewHeadCount -1}/${data.totalHeadCount}명 참여"
            binding.crewActivityDate.text = data.crewMeetDate
            binding.crewDibsCount.text = data.dibsCount + "명"
            binding.crewList.setOnClickListener {
                val bundle = Bundle()
                var detailCrewFragment: Fragment = CrewDetailFragment()
                bundle.putInt("crewIdx", data.crewIdx)
                bundle.putString("festivalTitle", titleStr[0].toString())
                detailCrewFragment.arguments = bundle
                mainActivity.supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, detailCrewFragment).commit()
            }
        }

    }
}