package com.example.tour.src.crew.make.finish

import android.os.Bundle
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.ActivityCrewMakeFinishBinding

class CrewMakeFinishActivity: BaseActivity<ActivityCrewMakeFinishBinding>(ActivityCrewMakeFinishBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.crewCollectSee.setOnClickListener {
//            var intent = Intent(this,CrewMakeFinishActivity::class.java)
//            startActivity(intent)
            finish()
        }
    }
}