package com.example.tour.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tour.config.BaseFragment
import com.example.tour.databinding.FragmentCrewAttendDetailBinding
import com.example.tour.R
import com.example.tour.detail.model.GetCrewDetailRes
import com.google.android.material.bottomsheet.BottomSheetBehavior

data class Perticipate(val userIdx : Int, val userProfileImageUrl : String ,val userNicName : String)
class CrewDetailFragment : BaseFragment<FragmentCrewAttendDetailBinding>
    (FragmentCrewAttendDetailBinding::bind, R.layout.fragment_crew_attend_detail),
    CrewDetailFragmentInterface {
lateinit var rvAdapter : CrewDetailAdapter
    private var data = arrayListOf<Perticipate>()
    lateinit var prevIntent : Intent
    lateinit var title : String
    private var crewIdx : Int? = null
    private var festivalTitle : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            crewIdx = it.getInt("crewIdx")
            festivalTitle = it.getString("festivalTitle")
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        var persistentBottomSheet = BottomSheetBehavior.from(binding.participateReconfirm);
        persistentBottomSheet.state=BottomSheetBehavior.STATE_HIDDEN

        CrewDetailFragmentService(this).tryGetCrewDetail(crewIdx!!)

        rvAdapter = CrewDetailAdapter(data, requireContext())
        binding.participateUserList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.participateUserList.adapter = rvAdapter

        persistentBottomSheet.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                //슬라이드 될 때
            }
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState) {
                    BottomSheetBehavior.STATE_COLLAPSED-> {
                    }
                    BottomSheetBehavior.STATE_DRAGGING-> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED-> {
                    }
                    BottomSheetBehavior.STATE_HIDDEN-> {
                    }
                    BottomSheetBehavior.STATE_SETTLING-> {
                    }
                }
            }
        })
        binding.participate.setOnClickListener {
            persistentBottomSheet.state=BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onGetCrewDetailSuccess(response: GetCrewDetailRes) {
        var size = response.result.participateUser.size
        when (response.message) {
            "요청에 성공하였습니다." -> {
                for (i in 0 until size){
                    data.add(Perticipate(response.result.participateUser[i].userIdx, response.result.participateUser[i].userProfileImageUrl, response.result.participateUser[i].userNickName))
                }
                rvAdapter.notifyDataSetChanged()
                rvAdapter = CrewDetailAdapter(data, requireContext())
                binding.crewLeaderNickname.text = response.result.participateUser[0].userNickName
                Glide.with(this).load(response.result.participateUser[0].userProfileImageUrl).into(binding.leaderImage)
                binding.festivalName.text = festivalTitle
                binding.crewHeadCount.text = response.result.totalHeadCount.toString() + "명"
                binding.dateTime.text = response.result.crewMeetDate + " / " + response.result.crewMeetTime
                binding.genderAge.text = response.result.crewGender + " / " + response.result.crewMinAge + "세" + " ~ " + response.result.crewMaxAge + "세"
                binding.crewContent.text = response.result.crewComment
                binding.participateHeadCount.text = "${response.result.crewHeadCount - 1}/${response.result.totalHeadCount}"
            }
            else -> {
                showCustomToast(response.message.toString())
            }
        }
    }

    override fun onGetCrewDetailFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}