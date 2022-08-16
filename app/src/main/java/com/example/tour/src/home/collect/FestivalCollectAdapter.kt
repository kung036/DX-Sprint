package com.example.tour.src.home.collect


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import com.bumptech.glide.Glide
import com.example.tour.databinding.ItemFestivalCollectBinding
//import com.example.tour.databinding.ItemHeaderBinding
import com.example.tour.src.home.collect.model.Item
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
//                Snackbar.make(it, "Item $layoutPosition touched!", Snackbar.LENGTH_SHORT).show()

//                // 데이터 전달
//                val bundle = Bundle()
//                var detailfragment:Fragment = DetailFragment()
//                bundle.putString("image_url", data.image)
//                bundle.putString("title", data.title)
//                bundle.putString("place", data.place)
//                bundle.putString("content", data.content)
//                if(data.date == data.time) {bundle.putString("date", data.date)}
//                else {bundle.putString("date", "${data.date}(${data.time})")}
//                bundle.putString("address", data.address)
//                bundle.putString("money", data.money)
//                bundle.putString("phoneNumber", data.phoneNumber)
//                bundle.putString("homepageURL", data.homepageURL)
//                bundle.putString("facility", data.facility)
//                bundle.putInt("festival_id", data.festival_id)
//                detailfragment.arguments = bundle
//
//                // 화면 전환
//                activity_binding.layoutTitle.text = "상세 페이지"
//                activity_binding.layoutImage.setImageResource(R.drawable.dashicons_arrow_right_alt)
//                mainActivity.supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, detailfragment).commit()

                //setData(layoutPosition)
            }
        }
    }

}