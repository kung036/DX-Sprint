package com.example.tour.src.crew

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tour.R
import com.example.tour.databinding.ItemRecycleCrewBinding
import com.example.tour.src.crew.detail.CrewDetailActivity
import com.example.tour.src.home.MainActivity

private lateinit var binding : ItemRecycleCrewBinding
class CrewAdapter(private val dataSet: ArrayList<PopularCrew>, var con : Context, private var mainActivity: MainActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1
    private val TYPE_FOOTER = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemRecycleCrewBinding.inflate(LayoutInflater.from(parent.context),parent, false)
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

    inner class ViewHolder(private val binding : ItemRecycleCrewBinding) : RecyclerView.ViewHolder(binding.root) {
        //var textData = arrayListOf<CrewParticipate>()
        fun bind(data: PopularCrew) {
            Glide.with(con).load(data.festivalImageUrl).into(binding.crewViewImage)
            var titleStr = data.title.split("(")
            binding.crewName.text = data.crewName
            binding.crewCondition.text = data.crewGender
            binding.crewHeadCount.text = "${data.crewHeadCount -1}/${data.totalHeadCount}명 참여"
            binding.crewActivityDate.text = data.crewMeetDate
            binding.crewDibsCount.text = data.dibsCount + "명"

            // 크루 터치 이벤트
            binding.crewList.setOnClickListener {
                var intent = Intent(con, CrewDetailActivity::class.java)
                intent.putExtra("crewIdx", data.crewIdx)
                intent.putExtra("festivalTitle", titleStr[0].toString())
                intent.putExtra("crewName", data.crewName)
                intent.putExtra("image", data.festivalImageUrl)
                con.startActivity(intent)
            }
        }
    }
}