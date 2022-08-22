package com.example.tour.src.crew.make

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.Nullable
import com.example.tour.config.ApplicationClass
import com.example.tour.config.ApplicationClass.Companion.editor
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.*
import com.example.tour.src.crew.MainCrewFragmentService
import com.example.tour.src.crew.make.finish.CrewMakeFinishActivity
import com.example.tour.src.crew.make.model.GetFestivalResponse
import com.example.tour.src.crew.make.model.PostCrewReq
import com.example.tour.src.crew.make.model.PostCrewRes


class CrewMakeActivity : BaseActivity<ActivityCrewMakeBinding>(ActivityCrewMakeBinding::inflate),
    MakeCrewActivityInterface{
    private var minAge = 0
    private var maxAge = 0
    private var title = ""
    private var festivalIdx = 0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editor.putString("selectDay","")
        editor.commit()
        var intent = intent
        binding.festivalSearch.text = intent.getStringExtra("title").toString()

        binding.headCountPlus.setOnClickListener {
            if(binding.headCount.text.toString().toInt() < 100) {
                binding.headCount.text = (binding.headCount.text.toString().toInt() + 1).toString()
            }else{
                binding.headCount.text = (binding.headCount.text.toString().toInt()).toString()
            }
        }
        binding.headCountMinus.setOnClickListener {
            if(binding.headCount.text.toString().toInt() > 0){
                binding.headCount.text = (binding.headCount.text.toString().toInt() - 1).toString()
            }else{
                binding.headCount.text = (binding.headCount.text.toString().toInt()).toString()
            }
        }
        binding.llDate.setOnClickListener {

            val dialog = DateBottomSheetFragment(this)
            dialog.setDialogListener(object : DateBottomSheetFragment.CustomDialogListener {
                override fun onPositiveClicked(date: String) {
                    Log.d("test",date.toString())
                    binding.tvDate.text = date.toString()
                }
                override fun onNegativeClicked() {}
            })
            dialog.show(supportFragmentManager, "bottomSheet")
        }
        binding.llTime.setOnClickListener {

            val dialog = TimeBottomSheetFragment(this)
            dialog.setDialogListener(object : TimeBottomSheetFragment.CustomDialogListener {
                override fun onPositiveClicked(date: String) {
                    Log.d("test",date.toString())
                    binding.tvTime.text = date.toString()
                }
                override fun onNegativeClicked() {}
            })
            dialog.show(supportFragmentManager, "bottomSheet")
        }

        binding.llGender.setOnClickListener {
            var newIntent = Intent(this,GenderSelectActivity::class.java)
            startActivityForResult(newIntent,1234)
        }
        binding.llAge.setOnClickListener {
            var newIntent = Intent(this,AgeSelectActivity::class.java)
            startActivityForResult(newIntent,5678)
        }
        binding.festivalSearch.setOnClickListener {
            val dialog = FestivalNameBottomSheetFragment(this)
            dialog.setDialogListener(object : FestivalNameBottomSheetFragment.CustomDialogListener {
                override fun onPositiveClicked(titleSearch: String, festivalIdxSearch: Int) {

                    binding.festivalSearch.text = titleSearch.toString()
                    festivalIdx = festivalIdxSearch
                }
                override fun onNegativeClicked() {}
            })
            dialog.show(supportFragmentManager, "bottomSheet")

        }
        binding.crewButtonMake.setOnClickListener {
            var crewName = binding.crewName.text.toString()
            var crewComment = binding.crewContent.text.toString()
            var crewHeadCount = binding.headCount.text.toString().toInt()
            var crewMeetDate = binding.tvDate.text.toString()
            var crewMeetTime = binding.tvTime.text.toString()
            var crewGender = binding.tvGender.text.toString()
            var crewMinAge = minAge
            var crewMaxAge = maxAge

            if(crewName.length < 2){
                Toast.makeText(this, "크루 이름은 2자 이상으로 설정해주세요.", Toast.LENGTH_SHORT).show()
            }else if(crewComment.length < 5){
                Toast.makeText(this, "크루 설명은 5자 이상으로 설정해주세요.", Toast.LENGTH_SHORT).show()
            }else if(festivalIdx == 0){
                Toast.makeText(this, "축제를 선택해주세요.", Toast.LENGTH_SHORT).show()
            }else{
                var postCrewReq = PostCrewReq(UC_SEQ = festivalIdx, userIdx = ApplicationClass.sSharedPreferences.getInt(
                    ApplicationClass.USER_IDX,0), festivalTitle = title, crewName = crewName, crewComment = crewComment,
                crewHeadCount = crewHeadCount, crewMeetDate = crewMeetDate, crewMeetTime = crewMeetTime,
                    crewGender = crewGender, crewMinAge = crewMinAge, crewMaxAge = crewMaxAge)
                MakeCrewActivityService(this).tryPostCrew(postCrewReq)

                var intent = Intent(this,CrewMakeFinishActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            val myData = data!!.getIntExtra("gender",0)
            when (myData){
                1 -> binding.tvGender.text = "누구나"
                2 -> binding.tvGender.text = "남성만"
                3 -> binding.tvGender.text = "여성만"
            }
        }
        if (requestCode == 5678 && resultCode == RESULT_OK) {
            val min = data!!.getIntExtra("min",0)
            val max = data!!.getIntExtra("max",100)
            minAge = min
            maxAge = max
            binding.tvAge.text = "${min}세 ~ ${max}세"
        }
    }

    override fun onPostCrewSuccess(response: PostCrewRes) {
        when (response.message) {
            "요청에 성공하였습니다." -> {
                var testIntent = Intent(this,CrewMakeFinishActivity::class.java)
                startActivity(testIntent)
                finish()
            }
            else -> {
//                showCustomToast(response.message.toString())
            }
        }
    }

    override fun onPostCrewFailure(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onGetFestivalSuccess(response: GetFestivalResponse) {

    }

    override fun onGetFestivalFailure(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
