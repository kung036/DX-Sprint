package com.example.tour.src

import MypageFragment
import android.os.Bundle
import com.example.tour.R
import com.example.tour.config.BaseActivity
import com.example.tour.crew.MainCrewFragment
import com.example.tour.databinding.ActivityMainBinding

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
                        binding.layoutTitle.text = "검색"
                        binding.layoutImage.setImageResource(R.drawable.dashicons_arrow_right_alt)
                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commit()
                    }
                    R.id.main_btn_mypage -> {
                        binding.layoutTitle.text = "나의 정보"
                        binding.layoutImage.setImageResource(R.drawable.dashicons_arrow_right_alt)
                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MypageFragment()).commit()
                    }
                }
                true
            }
        }
    }

    // 오늘 날짜 설정
//    private var findDate = SimpleDateFormat("yyyy-MM-dd 00:00:00", Locale.getDefault()).format(Date(System.currentTimeMillis()))

    // 날짜 설정하기
//    inner class DataSetListener:DatePickerDialog.OnDateSetListener {
//        override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
//            findDate = String.format("%d-%02d-%02d 00:00:00", p1, p2+1, p3)
//            binding.
//        }
//    }
}