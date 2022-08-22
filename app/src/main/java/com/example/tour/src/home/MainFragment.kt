package com.example.tour.src.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tour.R
import com.example.tour.config.ApplicationClass.Companion.NICKNAME_TOKEN
import com.example.tour.config.ApplicationClass.Companion.sSharedPreferences
import com.example.tour.config.BaseFragment
import com.example.tour.src.home.MainActivity
import com.example.tour.databinding.FragmentMainBinding
import com.example.tour.databinding.ItemRecycleMainBinding
import com.example.tour.src.crew.CrewAdapter
import com.example.tour.src.crew.MainCrewFragmentService
import com.example.tour.src.crew.PopularCrew
import com.example.tour.src.crew.make.CrewMakeActivity
import com.example.tour.src.home.allCrew.AllCrewActivity
import com.example.tour.src.home.collect.FestivalCollectActivity
import com.example.tour.src.home.detail.DetailActivity
import com.example.tour.src.home.model.GetCrewRes
import com.example.tour.src.home.model.GetFestivalResponse
import java.net.URL
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

var Test:JSONArray? = null

data class FewCrew(
    val crewIdx: Int, val festivalIdx: Int, val festivalImageUrl: String, val title: String, val crewName: String,
    val crewGender: String, val crewHeadCount: Int, val totalHeadCount: Int, val crewMeetDate: String,
    val dibsCount: String)

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::bind,
    R.layout.fragment_main), MainFragmentInterface{
    private val dataSet = arrayListOf<FewCrew>()
    private lateinit var rvAdapter: MainAdapter
    private var image_url = arrayListOf<String>()
    private var title = arrayListOf<String>()
    private var place = arrayListOf<String>()
    private var content = arrayListOf<String>()
    private var date = arrayListOf<String>()
    private var address = arrayListOf<String>()
    private var money = arrayListOf<String>()
    private var phoneNumber = arrayListOf<String>()
    private var homepageURL = arrayListOf<String>()
    private var facility = arrayListOf<String>()
    private var festivalIdx = arrayListOf<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 리사이클러 뷰
        rvAdapter = MainAdapter(dataSet, requireContext())
        binding.mainViewCrews.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.mainViewCrews.adapter = rvAdapter

        MainFragmentService(this).
        tryGetFestivals("iOsSg7SsQnn9oHGqbJo2A73DilcpwmIyKVrnq0puly4WPZgmww7UzNhpFisZn32fvFS2dCXuXE9kiu9I4L0kgg==",
        4, "json")

        MainFragmentService(this).tryGetFewCrews()

        binding.collectSee.setOnClickListener {
            val intent = Intent(context,FestivalCollectActivity::class.java)
            startActivity(intent)
        }
        binding.llFestival1.setOnClickListener {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("image_url",image_url[0])
            intent.putExtra("title",title[0])
            intent.putExtra("place",place[0])
            intent.putExtra("content",content[0])
            intent.putExtra("date",date[0])
            intent.putExtra("address",address[0])
            intent.putExtra("money",money[0])
            intent.putExtra("phoneNumber",phoneNumber[0])
            intent.putExtra("homepageURL",homepageURL[0])
            intent.putExtra("facility",facility[0])
            intent.putExtra("festivalIdx",festivalIdx[0])
            startActivity(intent)
        }
        binding.llFestival2.setOnClickListener{
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("image_url",image_url[1])
            intent.putExtra("title",title[1])
            intent.putExtra("place",place[1])
            intent.putExtra("content",content[1])
            intent.putExtra("date",date[1])
            intent.putExtra("address",address[1])
            intent.putExtra("money",money[1])
            intent.putExtra("phoneNumber",phoneNumber[1])
            intent.putExtra("homepageURL",homepageURL[1])
            intent.putExtra("facility",facility[1])
            intent.putExtra("festivalIdx",festivalIdx[1])
            startActivity(intent)
        }
        binding.llFestival3.setOnClickListener{
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("image_url",image_url[2])
            intent.putExtra("title",title[2])
            intent.putExtra("place",place[2])
            intent.putExtra("content",content[2])
            intent.putExtra("date",date[2])
            intent.putExtra("address",address[2])
            intent.putExtra("money",money[2])
            intent.putExtra("phoneNumber",phoneNumber[2])
            intent.putExtra("homepageURL",homepageURL[2])
            intent.putExtra("facility",facility[2])
            intent.putExtra("festivalIdx",festivalIdx[2])
            startActivity(intent)
        }
        binding.llFestival4.setOnClickListener{
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("image_url",image_url[3])
            intent.putExtra("title",title[3])
            intent.putExtra("place",place[3])
            intent.putExtra("content",content[3])
            intent.putExtra("date",date[3])
            intent.putExtra("address",address[3])
            intent.putExtra("money",money[3])
            intent.putExtra("phoneNumber",phoneNumber[3])
            intent.putExtra("homepageURL",homepageURL[3])
            intent.putExtra("facility",facility[3])
            intent.putExtra("festivalIdx",festivalIdx[3])
            startActivity(intent)
        }
        binding.llFestival5.setOnClickListener{
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("image_url",image_url[4])
            intent.putExtra("title",title[4])
            intent.putExtra("place",place[4])
            intent.putExtra("content",content[4])
            intent.putExtra("date",date[4])
            intent.putExtra("address",address[4])
            intent.putExtra("money",money[4])
            intent.putExtra("phoneNumber",phoneNumber[4])
            intent.putExtra("homepageURL",homepageURL[4])
            intent.putExtra("facility",facility[4])
            intent.putExtra("festivalIdx",festivalIdx[4])
            startActivity(intent)
        }
        binding.createCrew.setOnClickListener {
            var nickName = sSharedPreferences.getString(NICKNAME_TOKEN, "EMPTY")
            if (nickName == "EMPTY") {
                Toast.makeText(context, "로그인이 필요한 기능입니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(context, CrewMakeActivity::class.java)
                intent.putExtra("title", "")
                startActivity(intent)
            }
        }
        binding.moreArrow.setOnClickListener {
            val intent = Intent(context, AllCrewActivity::class.java)
            startActivity(intent)
        }
        binding.participateCrew.setOnClickListener {
            val intent = Intent(context,AllCrewActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onGetFestivalSuccess(response: GetFestivalResponse) {

        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/dx-sprint-e06b6.appspot.com/o/banner_festival_find1.png?alt=media&token=baa24218-f159-4c7c-b73f-73783150f4b6").
                    into(binding.thumbnailImage1)
        var str = response.getFestivalKr.item[0].MAIN_TITLE.split("(")
        binding.title1.text = "부산푸드필름페스타"
        binding.date1.text = "2022.7.1 ~ 2022.7.3"
        title.add("부산푸드필름페스타")

        Glide.with(this).load(response.getFestivalKr.item[0].MAIN_IMG_NORMAL).into(binding.thumbnailImage2)
        str = response.getFestivalKr.item[0].MAIN_TITLE.split("(")
        binding.title2.text = str[0].toString()
        binding.date2.text = response.getFestivalKr.item[0].USAGE_DAY_WEEK_AND_TIME
        title.add(str[0])

        Glide.with(this).load(response.getFestivalKr.item[1].MAIN_IMG_NORMAL).into(binding.thumbnailImage3)
        str = response.getFestivalKr.item[1].MAIN_TITLE.split("(")
        binding.title3.text = str[0].toString()
        binding.date3.text = response.getFestivalKr.item[1].USAGE_DAY_WEEK_AND_TIME
        title.add(str[0])

        Glide.with(this).load(response.getFestivalKr.item[2].MAIN_IMG_NORMAL).into(binding.thumbnailImage4)
        str = response.getFestivalKr.item[2].MAIN_TITLE.split("(")
        binding.title4.text = str[0].toString()
        binding.date4.text = response.getFestivalKr.item[2].USAGE_DAY_WEEK_AND_TIME
        title.add(str[0])

        Glide.with(this).load(response.getFestivalKr.item[3].MAIN_IMG_NORMAL).into(binding.thumbnailImage5)
        str = response.getFestivalKr.item[3].MAIN_TITLE.split("(")
        binding.title5.text = str[0].toString()
        binding.date5.text = response.getFestivalKr.item[3].USAGE_DAY_WEEK_AND_TIME
        title.add(str[0])

        image_url.add("https://firebasestorage.googleapis.com/v0/b/dx-sprint-e06b6.appspot.com/o/banner_festival_find1.png?alt=media&token=baa24218-f159-4c7c-b73f-73783150f4b6")
        place.add("부산푸드필름페스타")
        content.add("영화에 음식의 맛을 더한 축제\n음식과 영화를 매개로 한 다양한 컨텐츠\n\"술 마시는 인류\"")
        date.add("2022. 7. 1. ~ 7. 3.\n" +
                "프로그램별 상이(홈페이지 참조)")
        address.add("부산푸드필름페스타")
        money.add("프로그램별 상이(홈페이지 참조)")
        phoneNumber.add("051-710-6948")
        homepageURL.add("https://www.bfff.kr/bbs/content.php?co_id=bfff")
        facility.add("장애인 전용 관람석, 점자블록, 엘리베이터, 장애인 주차구역, 휠체어접근 가능, 장애인 화장실")
        content.add("부산푸드필름페스타는 ‘영화’와 ‘음식’을 매개로 다양한 푸드콘텐츠를 즐길 수 있는 음식영화 축제의 장입니다.\n" +
                "섹션 별로 나누어진 다양한 영화상영 프로그램을 즐기고 전문가와 함께 영화 속 음식이야기를 나눕니다. 포트럭테이블을 비롯하여, 푸드테라피존, 수제맥주존, 푸드트럭존, 플리마켓 등 풍성한 야외 부대행사도 진행됩니다.\n" +
                "\n" +
                "영화 속 음식이 궁금하세요? 부산 로컬 음식을 맛보고 싶으신가요? 그렇다면 이번 이색영화축제를 놓치지 말아야죠.^^\n" +
                "\n" +
                "영화를 맛있게 감상하는 방법, 부산푸드필름페스타로 오세요!")
        festivalIdx.add(3333)

        for(i in 0 until 4){
            image_url.add(response.getFestivalKr.item[i].MAIN_IMG_NORMAL)
            place.add(response.getFestivalKr.item[i].MAIN_PLACE)
            content.add(response.getFestivalKr.item[i].ITEMCNTNTS)
            if(response.getFestivalKr.item[i].USAGE_DAY.equals("")){
                date.add(response.getFestivalKr.item[i].USAGE_DAY_WEEK_AND_TIME)
            }else{
                date.add(response.getFestivalKr.item[i].USAGE_DAY)
            }
            if(response.getFestivalKr.item[i].ADDR1.equals("")){
                address.add(response.getFestivalKr.item[i].MAIN_PLACE)
            }else{
                address.add(response.getFestivalKr.item[i].ADDR1)
            }
            money.add(response.getFestivalKr.item[i].USAGE_AMOUNT)
            phoneNumber.add(response.getFestivalKr.item[i].CNTCT_TEL)
            homepageURL.add(response.getFestivalKr.item[i].HOMEPAGE_URL)
            facility.add(response.getFestivalKr.item[i].MIDDLE_SIZE_RM1)
            festivalIdx.add(response.getFestivalKr.item[i].UC_SEQ)
            content.add(response.getFestivalKr.item[i].ITEMCNTNTS)
        }
    }

    override fun onGetFestivalFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onGetFewCrewSuccess(response: GetCrewRes) {
        var size = response.result.size
        when (response.message) {
            "요청에 성공하였습니다." -> {
                for (i in 0 until 3){
                    dataSet.add(
                        FewCrew(response.result[i].crewIdx,response.result[i].festivalIdx, response.result[i].festivalImageUrl,
                            response.result[i].title, response.result[i].crewName, response.result[i].crewGender,
                            response.result[i].crewHeadCount,response.result[i].totalHeadCount, response.result[i].crewMeetDate,
                            response.result[i].dibsCount)
                    )
                }
                rvAdapter.notifyDataSetChanged()
                rvAdapter = MainAdapter(dataSet, requireContext())
                binding.mainViewCrews.adapter = rvAdapter
            }
            else -> {
                Toast.makeText(context, response.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onGetFewCrewFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}