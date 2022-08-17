package com.example.tour.src.crew.make

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tour.databinding.FragmentBottomSheetFestivalNameBinding
import com.example.tour.src.crew.make.model.GetFestivalResponse
import com.example.tour.src.crew.make.model.Item
import com.example.tour.src.crew.make.model.PostCrewReq
import com.example.tour.src.crew.make.model.PostCrewRes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private lateinit var binding: FragmentBottomSheetFestivalNameBinding

class FestivalNameBottomSheetFragment(context: Context) : BottomSheetDialogFragment(), MakeCrewActivityInterface{
    private var hour1:Int = 0
    private var min1:Int = 0
    private var customDialogListener: CustomDialogListener? = null

    private val dataSet = arrayListOf<Item>()
    private lateinit var rvAdapter: CrewMakeAdapter

    private var festivalIdx = 0
    private var image_url:String? = null
    private var title:String? = ""
    private var place:String? = null
    private var content:String? = null
    private var date:String? = null
    private var address:String? = null
    private var money:String? = null
    private var phoneNumber:String? = null
    private var homepageURL:String? = null
    private var facility:String? = null

    //인터페이스 설정
    interface CustomDialogListener {
        fun onPositiveClicked(titleSearch: String, festivalIdxSearch: Int)
        fun onNegativeClicked()
    }

    //호출할 리스너 초기화
    fun setDialogListener(customDialogListener: CustomDialogListener) {
        this.customDialogListener = customDialogListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBottomSheetFestivalNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAdapter = CrewMakeAdapter(dataSet, requireContext())
        binding.rvSearchFestival.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        MakeCrewActivityService(this).
        tryGetFestivals("iOsSg7SsQnn9oHGqbJo2A73DilcpwmIyKVrnq0puly4WPZgmww7UzNhpFisZn32fvFS2dCXuXE9kiu9I4L0kgg==",
            100, "json")
        rvAdapter.setOnItemClickListener(object : CrewMakeAdapter.OnItemClickListener {
            override fun onItemClick(v: View?, titleSearch: String, festivalIdxSearch: Int) {
                title = titleSearch
                festivalIdx = festivalIdxSearch
                customDialogListener?.onPositiveClicked(title!!, festivalIdx)
                dialog?.dismiss()
            }
        })
        binding.rvSearchFestival.adapter = rvAdapter

    }

    override fun onPostCrewSuccess(response: PostCrewRes) {

    }

    override fun onPostCrewFailure(message: String) {
        Toast.makeText(context,"오류 : $message",Toast.LENGTH_SHORT).show()
    }

    override fun onGetFestivalSuccess(response: GetFestivalResponse) {
        var size = response.getFestivalKr.item.size
        for(i in 0 until size){
            dataSet.add(response.getFestivalKr.item[i])
        }
        rvAdapter.notifyDataSetChanged()
        rvAdapter = CrewMakeAdapter(dataSet, requireContext())
        //binding.rvSearchFestival.layoutManager =
            //LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        //binding.rvSearchFestival.adapter = rvAdapter
    }

    override fun onGetFestivalFailure(message: String) {
        Toast.makeText(context,"오류 : $message",Toast.LENGTH_SHORT).show()
    }
}