//package com.example.tour.src.crew.detail
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.bumptech.glide.Glide
//import com.example.tour.config.BaseFragment
//import com.example.tour.R
//import com.example.tour.config.BaseActivity
//import com.example.tour.databinding.ActivityCrewAttendBinding
//import com.example.tour.databinding.ActivityCrewAttendDetailBinding
//import com.example.tour.src.crew.crewAttend.CrewAttendActivityInterface
//import com.example.tour.src.crew.detail.model.GetCrewDetailRes
//import com.google.android.material.bottomsheet.BottomSheetBehavior
//
//data class Participate(val userIdx : Int, val userProfileImageUrl : String ,val userNicName : String)
//
////class CrewAttendDetailActivity : BaseFragment<ActivityCrewAttendDetailBinding>
////    (ActivityCrewAttendDetailBinding::bind, R.layout.activity_crew_attend_detail),
////    CrewAttendDetailActivityInterface {
//class CrewAttendDetailActivity : BaseActivity<ActivityCrewAttendDetailBinding>
//        (ActivityCrewAttendDetailBinding::inflate), CrewAttendDetailActivityInterface {
//    lateinit var rvAdapter : CrewDetailAdapter
//    private var data = arrayListOf<Participate>()
//    lateinit var prevIntent : Intent
//    private  var title : String? = null
//    private var crewIdx : Int? = null
//    private var festivalTitle : String? = null
//    private var crewName:String? = null
//    private var place:String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Apator 내용 받아오기
//        crewIdx = intent.getIntExtra("crewIdx", -1)
//        festivalTitle = intent.getStringExtra("festivalTitle")
//        crewName = intent.getStringExtra("crewName")
//        place = intent.getStringExtra("place")
//
//        var persistentBottomSheet = BottomSheetBehavior.from(binding.participateReconfirm);
//        persistentBottomSheet.state=BottomSheetBehavior.STATE_HIDDEN
//
//        CrewAttendDetailActivityService(this).tryGetCrewDetail(crewIdx!!)
//
//        rvAdapter = CrewDetailAdapter(data, this)
//        binding.participateUserList.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
//        binding.participateUserList.adapter = rvAdapter
//
//        persistentBottomSheet.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                //슬라이드 될 때
//            }
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                when(newState) {
//                    BottomSheetBehavior.STATE_COLLAPSED-> {
//                    }
//                    BottomSheetBehavior.STATE_DRAGGING-> {
//                    }
//                    BottomSheetBehavior.STATE_EXPANDED-> {
//                    }
//                    BottomSheetBehavior.STATE_HIDDEN-> {
//                    }
//                    BottomSheetBehavior.STATE_SETTLING-> {
//                    }
//                }
//            }
//        })
//        binding.participate.setOnClickListener {
//            persistentBottomSheet.state=BottomSheetBehavior.STATE_EXPANDED
//        }
//    }
//
//    override fun onGetCrewDetailSuccess(response: GetCrewDetailRes) {
//        var size = response.result.participateUser.size
//        when (response.message) {
//            "요청에 성공하였습니다." -> {
//                for (i in 0 until size){
//                    data.add(Participate(response.result.participateUser[i].userIdx, response.result.participateUser[i].userProfileImageUrl, response.result.participateUser[i].userNickName))
//                }
//                rvAdapter.notifyDataSetChanged()
//                rvAdapter = CrewDetailAdapter(data, this)
//                binding.crewLeaderNickname.text = response.result.participateUser[0].userNickName
//                Glide.with(this).load(response.result.participateUser[0].userProfileImageUrl).into(binding.leaderImage)
//                binding.festivalName.text = festivalTitle
//                Log.d("shin", "detail : "+place)
//                binding.crewName.text = crewName
//                binding.crewHeadCount.text = response.result.totalHeadCount.toString() + "명"
//                binding.dateTime.text = response.result.crewMeetDate + " / " + response.result.crewMeetTime
//                binding.genderAge.text = response.result.crewGender + " / " + response.result.crewMinAge + "세" + " ~ " + response.result.crewMaxAge + "세"
////                binding.crewContent.text = response.result.crewComment
//                binding.participateHeadCount.text = "${response.result.crewHeadCount - 1}/${response.result.totalHeadCount}"
//            }
//            else -> {
//                showCustomToast(response.message.toString())
//            }
//        }
//    }
//
//    override fun onGetCrewDetailFailure(message: String) {
//        showCustomToast("오류 : $message")
//    }
//}