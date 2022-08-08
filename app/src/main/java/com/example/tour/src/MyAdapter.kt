package com.example.tour.src


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.example.tour.R
//import com.example.tour.databinding.ItemHeaderBinding
import com.example.tour.databinding.FragmentRecycleMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList
//import com.example.tour.src.MyAdapter.HeaderViewHolder

private lateinit var binding: FragmentRecycleMainBinding
//private lateinit var headerBinding: ItemHeaderBinding

class MyAdapter(private val dataSet: ArrayList<card>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        if(viewType == TYPE_HEADER){
//            headerBinding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context),parent, false)
//            return HeaderViewHolder(headerBinding)
//        }
//        else{
            binding = FragmentRecycleMainBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            return ViewHolder(binding)
//        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
//            is HeaderViewHolder -> holder.bind()
            is ViewHolder -> holder.bind(dataSet[position])
        }
//        holder.bind(dataSet[position])

    }
    override fun getItemViewType(position: Int): Int {
//        return if (position == 0) TYPE_HEADER else TYPE_ITEM
        return TYPE_ITEM
    }
    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(private val binding: FragmentRecycleMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: card) {
            binding.mainViewImage.setImageResource(data.main_view_image)
            binding.mainViewDate.text = data.main_view_date
            binding.mainViewTitle.text = data.main_view_title
            binding.mainViewPlace.text = data.main_view_place

            // 터치했을 때
            binding.mainViewLayout.setOnClickListener {
                Snackbar.make(it, "Item $layoutPosition touched!", Snackbar.LENGTH_SHORT).show()
                //setData(layoutPosition)
            }
        }
    }
//    class HeaderViewHolder(private val headerBinding: ItemHeaderBinding) : RecyclerView.ViewHolder(headerBinding.root) {
//        fun bind() {
//        }
//    }

    fun removeData(position: Int) {
        dataSet.removeAt(position)
        notifyItemRemoved(position)
    }

    // 두 개의 뷰홀더 포지션을 받아 Collections.swap으로 첫번째 위치와 두번째 위치의 데이터를 교환
    fun swapData(fromPos: Int, toPos: Int) {
        Collections.swap(dataSet, fromPos, toPos)
        notifyItemMoved(fromPos, toPos)
    }
//    // 선택한 뷰홀더 포지션의 데이터 내용을 바꾸도록 함
//    fun setData(position: Int) {
//        dataSet[position] = card(com.example.tour.R.drawable.thumbnail_image1_ntime,
//            "2022.7.30 토 ~ 2022.08.07 일",
//            "부산바다축제(한, 영, 중간, 중버, 일)",
//            "해운대 해수욕장, 광안리 해수욕장, 다대포 해수욕장 등")
//        notifyItemChanged(position)
//    }

}