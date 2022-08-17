package com.example.tour.src.home


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.example.tour.databinding.ItemRecycleCrewBinding
import com.example.tour.src.home.detail.DetailActivity
import com.example.tour.src.my.login.MyLoginActivity
//import com.example.tour.databinding.ItemHeaderBinding
import kotlin.collections.ArrayList

private lateinit var binding: ItemRecycleCrewBinding

class MainAdapter(private val dataSet: ArrayList<FewCrew>, private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ITEM = 1

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
        return TYPE_ITEM
    }
    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(private val binding: ItemRecycleCrewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FewCrew) {
            Glide.with(context).load(data.festivalImageUrl).into(binding.crewViewImage)
            binding.crewName.text = data.crewName
            binding.crewCondition.text = data.crewGender
            binding.crewHeadCount.text = "${data.totalHeadCount}명 모집"
            binding.crewActivityDate.text = data.crewMeetDate
            binding.crewDibsCount.text = data.dibsCount.toString() + "명"

            binding.crewList.setOnClickListener {
//                Toast.makeText(context,"test",Toast.LENGTH_SHORT).show()
                Intent(context, DetailActivity::class.java).apply {
                    putExtra("data", "data")
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context. startActivity(this) }
            }
        }
    }

}