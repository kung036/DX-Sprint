package com.example.tour.src.crew.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tour.config.ApplicationClass
import com.example.tour.config.ApplicationClass.Companion.NICKNAME_TOKEN
import com.example.tour.config.ApplicationClass.Companion.USER_IDX
import com.example.tour.config.ApplicationClass.Companion.sSharedPreferences
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.ActivityCrewAttendDetailBinding
import com.example.tour.src.chat.room.RoomActivity
import com.example.tour.src.crew.detail.model.GetCrewDetailRes
import com.example.tour.src.crew.detail.model.GetRoomIdxRes
import com.example.tour.src.crew.detail.model.PostParticipateCrewReq
import com.example.tour.src.crew.detail.model.PostParticipateCrewRes
import com.google.android.material.bottomsheet.BottomSheetBehavior

data class Participate(val userIdx : Int, val userProfileImageUrl : String ,val userNicName : String)
class CrewDetailActivity : BaseActivity<ActivityCrewAttendDetailBinding>(ActivityCrewAttendDetailBinding::inflate),
    CrewAttendDetailActivityInterface {
    lateinit var rvAdapter: CrewDetailAdapter
    private var data = arrayListOf<Participate>()
    private var image:String? = null
    lateinit var prevIntent: Intent
    lateinit var title: String
    private var crewIdx: Int = 0
    private var festivalTitle: String? = null
    private var crewLeaderIdx = 0
    private var pRoomIdx = 0
    private var userIdx = 0
    private var com = 0
    private var crewName:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent = intent

        crewIdx = intent.getIntExtra("crewIdx", 0)
        festivalTitle = intent.getStringExtra("festivalTitle")
        crewName = intent.getStringExtra("crewName")
        image = intent.getStringExtra("image")
        var persistentBottomSheet = BottomSheetBehavior.from(binding.participateReconfirm);
        persistentBottomSheet.state = BottomSheetBehavior.STATE_HIDDEN

        CrewAttendDetailActivityService(this).tryGetCrewDetail(crewIdx)

        rvAdapter = CrewDetailAdapter(data, this)
        binding.participateUserList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.participateUserList.adapter = rvAdapter

        persistentBottomSheet.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                //슬라이드 될 때
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }
        })
        binding.participate.setOnClickListener {
            var nickName = sSharedPreferences.getString(NICKNAME_TOKEN, "EMPTY")
            if (nickName == "EMPTY") {
                Toast.makeText(this, "로그인이 필요한 기능입니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                persistentBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        binding.participateConfirm.setOnClickListener {
            CrewAttendDetailActivityService(this).tryGetCrewRoomIdx(crewLeaderIdx, crewIdx)
            Log.d("roomIdx_in",pRoomIdx.toString())
        }
    }

    override fun onGetCrewDetailSuccess(response: GetCrewDetailRes) {
        var size = response.result.participateUser.size
        when (response.message) {
            "요청에 성공하였습니다." -> {
                for (i in 0 until size) {
                    data.add(
                        Participate(
                            response.result.participateUser[i].userIdx,
                            response.result.participateUser[i].userProfileImageUrl,
                            response.result.participateUser[i].userNickName
                        )
                    )
                }
                rvAdapter.notifyDataSetChanged()
                rvAdapter = CrewDetailAdapter(data, this)
                crewLeaderIdx = response.result.userIdx
                Log.d("leaderIdx",crewLeaderIdx.toString())
                binding.crewLeaderNickname.text = response.result.participateUser[0].userNickName
                Glide.with(this).load(response.result.participateUser[0].userProfileImageUrl)
                    .into(binding.leaderImage)
                Glide.with(this).load(image)
                    .into(binding.crewAttendImage)
                binding.festivalName.text = festivalTitle
                binding.crewHeadCount.text = response.result.totalHeadCount.toString() + "명"
                binding.crewName.text = crewName
                binding.dateTime.text =
                    response.result.crewMeetDate + " / " + response.result.crewMeetTime
                binding.genderAge.text =
                    response.result.crewGender + " / " + response.result.crewMinAge + "세" + " ~ " + response.result.crewMaxAge + "세"
                binding.crewContent.text = response.result.crewComment
                binding.participateHeadCount.text =
                    "${response.result.crewHeadCount - 1}/${response.result.totalHeadCount}"
            }
            else -> {
                Toast.makeText(this, response.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onGetCrewDetailFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onPostParticipateCrewSuccess(response: PostParticipateCrewRes) {
        when (response.message) {
            "요청에 성공하였습니다." -> {
                com = 1
            }
            else -> {
//                Toast.makeText(this, response.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPostParticipateCrewFailure(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
     }

    override fun onGetCrewRoomIdxSuccess(response: GetRoomIdxRes) {
        when (response.message) {
            "요청에 성공하였습니다." -> {
                pRoomIdx = response.result.roomIdx
                Log.d("roomIdx",pRoomIdx.toString())

                var postParticipateCrewReq = PostParticipateCrewReq(crewIdx = crewIdx, roomIdx = pRoomIdx,
                    userIdx = ApplicationClass.sSharedPreferences.getInt(USER_IDX,0))
                CrewAttendDetailActivityService(this).tryPostParticipateCrew(postParticipateCrewReq)
                val roomIntent = Intent(this, RoomActivity::class.java)
                roomIntent.putExtra("roomIdx",pRoomIdx)
                startActivity(roomIntent)
                finish()
            }
            else -> {
//                Toast.makeText(this, response.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onGetCrewRoomIdxFailure(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}