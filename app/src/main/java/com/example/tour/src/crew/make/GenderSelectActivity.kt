package com.example.tour.src.crew.make

import android.R.attr.data
import android.content.Intent
import android.os.Bundle
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.ActivityGenderSelectBinding


class GenderSelectActivity : BaseActivity<ActivityGenderSelectBinding>(ActivityGenderSelectBinding::inflate) {
    private var gender:Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gender = 1
        binding.rbGenderFree.setOnClickListener {
            if(binding.rbGenderFree.isChecked){
                binding.rbGenderMale.isChecked = false
                binding.rbGenderFemale.isChecked = false
                gender = 1
            }
        }
        binding.rbGenderMale.setOnClickListener {
            if(binding.rbGenderMale.isChecked){
                binding.rbGenderFree.isChecked = false
                binding.rbGenderFemale.isChecked = false
                gender = 2
            }
        }
        binding.rbGenderFemale.setOnClickListener {
            if(binding.rbGenderFemale.isChecked){
                binding.rbGenderMale.isChecked = false
                binding.rbGenderFree.isChecked = false
                gender = 3
            }
        }
        binding.genderSelect.setOnClickListener {
            val intent = Intent()

            intent.putExtra("gender", gender)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}