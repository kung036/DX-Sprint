package com.example.tour.src.crew.make

import android.content.Intent
import android.os.Bundle
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.ActivityAgeSelectBinding

class AgeSelectActivity : BaseActivity<ActivityAgeSelectBinding>(ActivityAgeSelectBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.ageSelect.setOnClickListener {
            val intent = Intent()

            intent.putExtra("min", binding.minAge.text.toString().toInt())
            intent.putExtra("max", binding.maxAge.text.toString().toInt())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}