package com.example.tour.src.home.collect


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import android.content.Intent
import com.bumptech.glide.Glide
import com.example.tour.databinding.ItemFestivalCollectBinding
//import com.example.tour.databinding.ItemHeaderBinding
import com.example.tour.src.home.collect.model.Item
import com.example.tour.src.home.detail.DetailActivity
import java.util.*
import kotlin.collections.ArrayList
//import com.example.tour.src.MyAdapter.HeaderViewHolder

private lateinit var binding: ItemFestivalCollectBinding

class FestivalCollectAdapter(private val dataSet: ArrayList<Item>,
                             private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemFestivalCollectBinding.inflate(LayoutInflater.from(parent.context),parent, false)

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

    inner class ViewHolder(private val binding: ItemFestivalCollectBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Item) {
            Glide.with(context).load(data.MAIN_IMG_NORMAL).into(binding.mainViewImage)

            binding.mainViewDate.text = data.USAGE_DAY_WEEK_AND_TIME
            var str = data.MAIN_TITLE.split("(")
            binding.mainViewTitle.text = str[0].toString()
            binding.mainViewPlace.text = data.MAIN_PLACE

            // 터치했을 때
            binding.mainViewLayout.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("image_url",data.MAIN_IMG_NORMAL)
                var str = data.MAIN_TITLE.split("(")
                intent.putExtra("title", str[0])
                intent.putExtra("place",data.MAIN_PLACE)
                intent.putExtra("content",data.ITEMCNTNTS)
                if(data.USAGE_DAY.equals("")){
                    intent.putExtra("date",data.USAGE_DAY_WEEK_AND_TIME)
                }else{
                    intent.putExtra("date",data.USAGE_DAY)
                }
                if(data.ADDR1.equals("")){
                    intent.putExtra("address",data.MAIN_PLACE)
                }else{
                    intent.putExtra("address",data.ADDR1)
                }
                intent.putExtra("money",data.USAGE_AMOUNT)
                intent.putExtra("phoneNumber", data.CNTCT_TEL)
                intent.putExtra("homepageURL",data.HOMEPAGE_URL)
                intent.putExtra("facility",data.MIDDLE_SIZE_RM1)
                intent.putExtra("festivalIdx",data.UC_SEQ)
                context.startActivity(intent)

            }
        }
    }

}