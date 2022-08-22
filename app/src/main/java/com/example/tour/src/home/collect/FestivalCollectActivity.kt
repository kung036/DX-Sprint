package com.example.tour.src.home.collect

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.ActivityFestivalCollectBinding
import com.example.tour.src.home.MainFragmentInterface
import com.example.tour.src.home.MainFragmentService
import com.example.tour.src.home.collect.model.Item
import com.example.tour.src.home.collect.model.GetFestivalResponse

class FestivalCollectActivity: BaseActivity<ActivityFestivalCollectBinding>(ActivityFestivalCollectBinding::inflate),
    FestivalCollectInterface{
    private val dataSet = arrayListOf<Item>()
    private lateinit var rvAdapter: FestivalCollectAdapter

    private var image_url:String? = null
    private var title:String? = null
    private var place:String? = null
    private var content:String? = null
    private var date:String? = null
    private var address:String? = null
    private var money:String? = null
    private var phoneNumber:String? = null
    private var homepageURL:String? = null
    private var facility:String? = null
    private var festival_id:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FestivalCollectService(this).
        tryGetFestivals("iOsSg7SsQnn9oHGqbJo2A73DilcpwmIyKVrnq0puly4WPZgmww7UzNhpFisZn32fvFS2dCXuXE9kiu9I4L0kgg==",
            100, "json")

        rvAdapter = FestivalCollectAdapter(dataSet, this)
        binding.mainViewFestival.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.mainViewFestival.adapter = rvAdapter

    }

    override fun onGetFestivalSuccess(response: GetFestivalResponse) {
        var size = response.getFestivalKr.item.size
        for(i in 0 until size){
            dataSet.add(response.getFestivalKr.item[i])
        }
        rvAdapter.notifyDataSetChanged()
        rvAdapter = FestivalCollectAdapter(dataSet, this)
        binding.mainViewFestival.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.mainViewFestival.adapter = rvAdapter
    }

    override fun onGetFestivalFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}