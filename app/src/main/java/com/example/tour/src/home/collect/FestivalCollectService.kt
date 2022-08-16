package com.example.tour.src.home.collect

import com.example.tour.config.ApplicationClass
import com.example.tour.src.home.collect.model.GetFestivalResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FestivalCollectService(val festivalCollectInterface: FestivalCollectInterface) {

    fun tryGetFestivals(serviceKey:String, numOfRows:Int, resultType:String){
        val festivalCollectRetrofitInterface = ApplicationClass.openRetrofit.create(
            FestivalCollectRetrofitInterface::class.java)
        festivalCollectRetrofitInterface.getCrews(serviceKey, numOfRows, resultType).enqueue(object : Callback<GetFestivalResponse> {
            override fun onResponse(call: Call<GetFestivalResponse>, response: Response<GetFestivalResponse>) {
                festivalCollectInterface.onGetFestivalSuccess(response.body() as GetFestivalResponse)
            }

            override fun onFailure(call: Call<GetFestivalResponse>, t: Throwable) {
                festivalCollectInterface.onGetFestivalFailure(t.message ?: "통신 오류")
            }
        })
    }
}