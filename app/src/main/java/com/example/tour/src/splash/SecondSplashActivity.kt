package com.example.tour.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.ActivitySecondSplashBinding
import com.example.tour.src.home.MainActivity

class SecondSplashActivity : BaseActivity<ActivitySecondSplashBinding>(ActivitySecondSplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },1000)
    }
}