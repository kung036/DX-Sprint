package com.example.tour.src.crew.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tour.databinding.ItemDetailCrewHeadCountFooterBinding
import com.example.tour.databinding.ItemDetailParticipateHeadCountBinding

private lateinit var binding : ItemDetailParticipateHeadCountBinding
private lateinit var footerBinding: ItemDetailCrewHeadCountFooterBinding
class CrewDetailAdapter(private val dataSet: ArrayList<Participate>, var con : Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1
    private val TYPE_FOOTER = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        if(viewType == TYPE_FOOTER){
            footerBinding = ItemDetailCrewHeadCountFooterBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            return FooterViewHolder(footerBinding)
        }
        else{
            binding = ItemDetailParticipateHeadCountBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            return ViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder -> holder.bind(dataSet[position])
            is FooterViewHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == dataSet.size) TYPE_FOOTER else TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return dataSet.size + 1
    }

    inner class ViewHolder(private val binding : ItemDetailParticipateHeadCountBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : Participate){
            Glide.with(con).load(data.userProfileImageUrl).into(binding.userProfileImg)
            binding.userNickname.text = data.userNicName
        }

    }
    inner class FooterViewHolder(private val binding : ItemDetailCrewHeadCountFooterBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(){}
    }
}