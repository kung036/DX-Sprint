package com.example.tour.src

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tour.R
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.ActivityMainBinding
import com.example.tour.src.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

//private lateinit var binding: ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commitAllowingStateLoss()

//        binding.mainNevi.run {
//            setOnItemSelectedListener {
        binding.mainNevi.run {
//            selectedItemId = R.id.framelaout_container
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.main_btn_home -> {
                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commit()
                    }
                    R.id.main_btn_crew -> {
                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commit()
                    }
                    R.id.main_btn_find -> {
                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commit()
                    }
                    R.id.main_btn_mypage -> {
                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commit()
                    }
                }
                true
            }
        }
//      binding.mainNevi.setOnNavigationItemSelectedListener(
//            BottomNavigationView.OnNavigationItemSelectedListener {
//            when(it.itemId) {
//                    R.id.main_btn_home -> {
//                        // 다른 프래그먼트 화면으로 이동하는 기능
//                        val mainFragment = MainFragment()
//                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commitAllowingStateLoss()
//                        return@OnNavigationItemSelectedListener true
//                    }
//                    R.id.main_btn_crew -> {
//                        val mainFragment = MainFragment()
//                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commit()
//                    }
//                    R.id.main_btn_find -> {
//                        val mainFragment = MainFragment()
//                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commit()
//                    }
//                    R.id.main_btn_mypage -> {
//                        val mainFragment = MainFragment()
//                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commit()
//                    }
//                }
//                false
//            })
    }
}