package com.example.tour.src.home

import android.content.Intent
import android.os.Bundle
import com.example.tour.R
import com.example.tour.config.BaseActivity
import com.example.tour.src.crew.MainCrewFragment
import com.example.tour.databinding.ActivityMainBinding
import com.example.tour.src.my.MyFragment
import com.example.tour.src.search.FindActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 첫화면
        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commit()
        binding.layoutImage.setImageResource(R.drawable.main_image_layout)
        binding.layoutTitle.text = "팡파레"

        binding.mainNevi.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.main_btn_home -> {
                        binding.layoutTitle.text = "팡파레"
                        binding.layoutImage.setImageResource(R.drawable.main_image_layout)
                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commit()
                    }
                    R.id.main_btn_crew -> {
                        binding.layoutTitle.text = "모임"
                        binding.layoutImage.setImageResource(R.drawable.dashicons_arrow_right_alt)
                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainCrewFragment()).commit()
                    }
                    R.id.main_btn_find -> {
//                        binding.layoutTitle.text = "검색"
//                        binding.layoutImage.setImageResource(R.drawable.dashicons_arrow_right_alt)
//                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commit()
                            var intent = Intent(context, FindActivity::class.java)
                            startActivity(intent)
                    }
                    R.id.main_btn_mypage -> {
                        binding.layoutTitle.text = "나의 정보"
                        binding.layoutImage.setImageResource(R.drawable.dashicons_arrow_right_alt)
                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MyFragment()).commit()
                    }
                }
                true
            }
        }
    }

}