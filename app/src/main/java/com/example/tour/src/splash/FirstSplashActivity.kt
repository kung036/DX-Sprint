package com.example.tour.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.ActivityFirstSplashBinding

class FirstSplashActivity : BaseActivity<ActivityFirstSplashBinding>(ActivityFirstSplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            intent = Intent(this,SecondSplashActivity::class.java)
            startActivity(intent)
            finish()
        },1000)

    }
}