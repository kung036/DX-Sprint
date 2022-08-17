package com.example.tour.src.home.detail

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.ActivityMainDetailBinding
import com.example.tour.src.crew.crewAttend.model.CrewAttendActivity
import com.example.tour.util.ImageURLClass
import com.example.tour.src.crew.make.CrewMakeActivity
import java.net.URL

class DetailActivity : BaseActivity<ActivityMainDetailBinding>(ActivityMainDetailBinding::inflate) {
    private var image_url:String? = null
    private var title:String? = null
    private var place:String? = null
    private var content:String? = null
    private var date:String? = null
    private var address:String? = null
    private var money:String? = null
    private var phoneNumber:String? = null
    private var homepageURL:String? = null
    private var facility:String? = null
    private var festival_id:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent = intent

            image_url = intent.getStringExtra("image_url")
            Log.d("test",image_url.toString())
            title = intent.getStringExtra("title")
            place = intent.getStringExtra("place")
            content = intent.getStringExtra("content")
            date = intent.getStringExtra("date")
            address = intent.getStringExtra("address")
            money = intent.getStringExtra("money")
            phoneNumber = intent.getStringExtra("phoneNumber")
            homepageURL = intent.getStringExtra("homepageURL")
            facility = intent.getStringExtra("facility")
            festival_id = intent.getIntExtra("festivalIdx",0)

            var image_task: ImageURLClass = ImageURLClass()
            image_task = ImageURLClass().apply {
                url = URL(image_url)
            }
            var bitmap: Bitmap = image_task.execute().get()

            //Glide.with(this).load(image_url).into(binding.detailImageVaner)
            binding.detailImageVaner.setImageBitmap(bitmap)
            binding.detailTextTitle.text = title
            //binding.detailTextPlace.text = place
            binding.detailTextContent.text = content
            binding.detailTextDate.text = date
            binding.detailTextAddress.text = address
            binding.detailTextMoney.text = money
            binding.detailTextPhonenumber.text = phoneNumber
            binding.detailTextHomepageURL.text = homepageURL
            binding.detailTextFacility.text = facility

        // 크루 만들기
        binding.imageFloatingCrewMake.setOnClickListener {
            val intent = Intent(this,CrewMakeActivity::class.java)
            intent.putExtra("title",title)
            startActivity(intent)
        }
        // 크루 참석하기
        binding.imageFloatingCrewAttend.setOnClickListener {
            // 데이터 전달
            val bundle = Bundle()
            var intent = Intent(this,CrewAttendActivity::class.java)
            intent.putExtra("image_url", image_url)
            intent.putExtra("title", title)
            intent.putExtra("place", place)
            intent.putExtra("festivalIdx", festival_id)
            startActivity(intent)
            finish()
            // 이미지 URL을 Bitmap으로 변경
            var image_task: ImageURLClass = ImageURLClass()
            image_task = ImageURLClass().apply {
                url = URL(image_url)
            }
            var bitmap: Bitmap = image_task.execute().get()

            // 선택한 카드 데이터 넣기
            binding.detailImageVaner.setImageBitmap(bitmap)
            binding.detailTextTitle.text = title
            //binding.detailTextPlace.text = place
            binding.detailTextContent.text = content
            binding.detailTextDate.text = date
            binding.detailTextAddress.text = address
            binding.detailTextMoney.text = money
            binding.detailTextPhonenumber.text = phoneNumber
            binding.detailTextHomepageURL.text = homepageURL
            binding.detailTextFacility.text = facility
        }
    }
}