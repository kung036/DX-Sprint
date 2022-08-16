package com.example.tour.src.my.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tour.databinding.ActivityMyLoginSuccessBinding
import com.example.tour.src.my.login.MyLoginActivity

private lateinit var binding: ActivityMyLoginSuccessBinding

//class MySignupFragment : Fragment() {
class MyLoginSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyLoginSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginSuccessButton.setOnClickListener {
            finish()
            startActivity(Intent(this, MyLoginActivity::class.java))
        }
    }
}