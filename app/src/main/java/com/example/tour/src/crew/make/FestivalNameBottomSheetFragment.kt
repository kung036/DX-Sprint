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
        dataSet.add(Item("부산광역시 해운대구 수영강변대로 120", "", "051-710-6948", "해운대구",
            "https://bfff.kr/", "부산푸드필름페스타는 ‘영화’와 ‘음식’을 매개로 다양한 푸드콘텐츠를 즐길 수 있는 음식영화 축제의 장입니다.\n" +
                    "섹션 별로 나누어진 다양한 영화상영 프로그램을 즐기고 전문가와 함께 영화 속 음식이야기를 나눕니다. 포트럭테이블을 비롯하여, 푸드테라피존, 수제맥주존, 푸드트럭존, 플리마켓 등 풍성한 야외 부대행사도 진행됩니다.\n" +
                    "\n" +
                    "영화 속 음식이 궁금하세요? 부산 로컬 음식을 맛보고 싶으신가요? 그렇다면 이번 이색영화축제를 놓치지 말아야죠.^^\n" +
                    "\n" +
                    "영화를 맛있게 감상하는 방법, 부산푸드필름페스타로 오세요!",
            35.17107, 129.12698, "https://firebasestorage.googleapis.com/v0/b/dx-sprint-e06b6.appspot.com/o/banner_festival_find1.png?alt=media&token=baa24218-f159-4c7c-b73f-73783150f4b6",
            "https://firebasestorage.googleapis.com/v0/b/dx-sprint-e06b6.appspot.com/o/banner_festival_find1.png?alt=media&token=baa24218-f159-4c7c-b73f-73783150f4b6",
            "부산푸드필름페스타", "부산푸드필름페스타(한, 영)", "장애인 전용 관람석, 점자블록, 엘리베이터, 장애인 주차구역, 휠체어접근 가능, 장애인 화장실",
            "부산푸드필름페스타", "온 가족이 함께 하는 맛있는 영화 축제", "부산푸드필름페스타", "도시철도 2호선 센텀시티역 6번 출구 도보 5분\\n버스 115, 181, 307, 해운대구3-1, 해운대구3-2 시청자미디어센터‧KNN방송국 하차 도보 8분",
            3333,"프로그램별 상이(홈페이지 참조)", "2022. 7. 1. ~ 7. 3.", "2022. 7. 1. ~ 7. 3.\\n프로그램별 상이(홈페이지 참조)"))

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