package com.example.tour.src

import android.app.DatePickerDialog
import android.graphics.*
//import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tour.R
import com.example.tour.api.RetrofitObject
import com.example.tour.api.VaccineBody
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.ActivityMainBinding
import com.example.tour.databinding.FragmentRecycleMainBinding
import com.example.tour.src.MainFragment
import com.example.tour.src.TestFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

//private lateinit var binding: ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // API 호출
        getVaccineStatus()

        // 처음 네비게이션바 화면
        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commitAllowingStateLoss()
        // 네비게이션바 설정
//        val onBottomNavigationSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener{ item ->
//                when (item.itemId) {
//                    R.id.main_btn_home -> {
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.framelaout_container, MainFragment()).commit()
//                    }
//                    R.id.main_btn_crew -> {
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.framelaout_container, TestFragment()).commit()
//                    }
//                    R.id.main_btn_find -> {
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.framelaout_container, MainFragment()).commit()
//                    }
//                    R.id.main_btn_mypage -> {
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.framelaout_container, MainFragment()).commit()
//                    }
//                }
//            true
//        }
//        binding.mainNevi.setOnItemSelectedListener(onBottomNavigationSelectedListener)

        binding.mainNevi.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.main_btn_home -> {
                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainFragment()).commit()
                    }
                    R.id.main_btn_crew -> {
                        supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, TestFragment()).commit()
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
    }

    // 오늘 날짜 설정
    private var findDate = SimpleDateFormat("yyyy-MM-dd 00:00:00", Locale.getDefault()).format(Date(System.currentTimeMillis()))

    // 백신 정보 불러오기
    private fun getVaccineStatus(){
        Log.d("print findDate", findDate)
//        RetrofitObject.getApiService().getInfo("json", findDate).enqueue(object : Callback<VaccineBody> {
//        RetrofitObject.getApiService().getInfo("json").enqueue(object : Callback<VaccineBody> {
//        RetrofitObject.getApiService().getInfo("json", 1, findDate).enqueue(object : Callback<VaccineBody>{
        RetrofitObject.getApiService().getInfo("json").enqueue(object : Callback<VaccineBody>{
            // api 호출 성공시
            override fun onResponse(call: Call<VaccineBody>, response: Response<VaccineBody>) {
//                setResponseText(response.code(), response.body())
                Log.d("shin", "retrofit success")
                Log.d("shin", "${response.code()}, ${response.body()}")

                Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()
            }

            // api 호출 실패시
            override fun onFailure(call: Call<VaccineBody>, t: Throwable) {
                Log.d("shin", "retrofit onFailure\n${t.printStackTrace()}")
                Toast.makeText(applicationContext, "fail", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setResponseText(resCode:Int, body:VaccineBody?){
        // 상태별 text 지정
//        binding.textviewResponse.text = when(resCode){
        when(resCode){
            200 -> {
                if(body == null){
                    "api body가 비어있습니다."
                }else{
//                    if(body.data.toString() == "[]"){
//                        "api호출은 성공했으나 해당 날짜에 데이터가 없습니다."
//                    }else{
//                        Log.d("shin", body.toString())
//                    }
                }
            }
            400 -> {
                "API 키가 만료됬거나 쿼리 파라미터가 잘못 됬습니다."
            }
            401 -> {
                "인증 정보가 정확하지 않습니다."
            }
            500 -> {
                "API 서버에 문제가 발생하였습니다."
            }
            else -> {
                "기타 문제발생..."
            }
        }
    }

    // 날짜 설정하기
//    inner class DataSetListener:DatePickerDialog.OnDateSetListener {
//        override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
//            findDate = String.format("%d-%02d-%02d 00:00:00", p1, p2+1, p3)
//            binding.
//        }
//    }
}