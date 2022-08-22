package com.example.tour.src.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import com.example.tour.R
import com.example.tour.config.ApplicationClass.Companion.NICKNAME_TOKEN
import com.example.tour.config.ApplicationClass.Companion.sSharedPreferences
import com.example.tour.config.BaseActivity
import com.example.tour.src.crew.MainCrewFragment
import com.example.tour.databinding.ActivityMainBinding
import com.example.tour.src.chat.ChatFragment
import com.example.tour.src.my.MyFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 첫화면
        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment())
            .commit()
        binding.layoutImage.setImageResource(R.drawable.logo_word_type)
        binding.layoutTitle.text = ""

        binding.mainNevi.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.main_btn_home -> {
                        binding.layoutTitle.text = ""
                        binding.layoutImage.setImageResource(R.drawable.logo_word_type)
                        binding.toolbar.setBackgroundColor(R.color.backgroundColor)
                        binding.toolbar.setBackgroundResource(R.color.backgroundColor)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.framelaout_container, MainFragment()).commit()
                    }
                    R.id.main_btn_crew -> {
                        binding.layoutTitle.text = "크루"
                        //binding.layoutImage.setImageResource(R.drawable.icons8_back_50)
                        binding.toolbar.setBackgroundColor(R.color.white)
                        binding.toolbar.setBackgroundResource(R.color.white)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.framelaout_container, MainCrewFragment()).commit()
                    }
                    R.id.main_btn_find -> {
                        var nickName = sSharedPreferences.getString(NICKNAME_TOKEN, "EMPTY")
                        if (nickName == "EMPTY") {
                            Toast.makeText(context, "로그인이 필요한 기능입니다.", Toast.LENGTH_SHORT).show()
                        } else {
                            binding.layoutTitle.text = "채팅방"
                            //binding.layoutImage.setImageResource(R.drawable.icons8_back_50)
                            binding.toolbar.setBackgroundColor(R.color.white)
                            binding.toolbar.setBackgroundResource(R.color.white)
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.framelaout_container, ChatFragment()).commit()
                        }
                    }
                    R.id.main_btn_mypage -> {
                        binding.layoutTitle.text = "마이 페이지"
                        //binding.layoutImage.setImageResource(R.drawable.icons8_back_50)
                        binding.toolbar.setBackgroundColor(R.color.white)
                        binding.toolbar.setBackgroundResource(R.color.white)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.framelaout_container, MyFragment()).commit()
                    }
                }
                true
            }
        }
    }

    //
    fun onMyFragmentChange() {
        binding.layoutTitle.text = "마이 페이지"
        //binding.layoutImage.setImageResource(R.drawable.icons8_back_50)
        binding.toolbar.setBackgroundColor(getResources().getColor(R.color.white))
        binding.toolbar.setBackgroundResource(R.color.white)
        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MyFragment())
            .commit()
    }

}