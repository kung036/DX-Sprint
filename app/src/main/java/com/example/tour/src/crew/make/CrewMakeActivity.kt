package com.example.tour.src.crew.make

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import com.example.tour.config.ApplicationClass
import com.example.tour.config.ApplicationClass.Companion.SELECT_DAY
import com.example.tour.config.ApplicationClass.Companion.editor
import com.example.tour.config.ApplicationClass.Companion.sSharedPreferences
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.*


class CrewMakeActivity : BaseActivity<ActivityCrewMakeBinding>(ActivityCrewMakeBinding::inflate){
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editor.putString("selectDay","")
        editor.commit()
        var intent = intent
        binding.festivalSearch.text = intent.getStringExtra("title").toString()

        binding.crewButtonMake.setOnClickListener {
            //supportFragmentManager.beginTransaction().
             //   replace(R.id.framelaout_container, CrewMakeFinishFragment()).commit()
        }
        binding.headCountPlus.setOnClickListener {
            if(binding.headCount.text.toString().toInt() < 100) {
                binding.headCount.text = (binding.headCount.text.toString().toInt() + 1).toString()
            }else{
                binding.headCount.text = (binding.headCount.text.toString().toInt()).toString()
            }
        }
        binding.headCountMinus.setOnClickListener {
            if(binding.headCount.text.toString().toInt() > 0){
                binding.headCount.text = (binding.headCount.text.toString().toInt() - 1).toString()
            }else{
                binding.headCount.text = (binding.headCount.text.toString().toInt()).toString()
            }
        }
        binding.llDate.setOnClickListener {
            val bottomSheetDialog = DateBottomSheetFragment()
            bottomSheetDialog.show(supportFragmentManager, "bottomSheet")
        }
    }

    override fun onRestart() {
        super.onRestart()
        showCustomToast("onRestart")
    }
    override fun onResume() {
        super.onResume()
        showCustomToast("onResume")
        var str = sSharedPreferences.getString(SELECT_DAY,"")
        if(!str.equals("")){
            binding.tvDate.text = str
        }
    }
}
