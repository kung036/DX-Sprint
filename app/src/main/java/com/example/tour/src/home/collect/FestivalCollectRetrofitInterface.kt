package com.example.tour.src.home.collect

import com.example.tour.src.home.collect.model.GetFestivalResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FestivalCollectRetrofitInterface {
    @GET("getFestivalKr")
    fun getCrews(@Query("serviceKey") serviceKey:String,
                @Query("numOfRows") numOfRows:Int,
                @Query("resultType") resultType:String): Call<GetFestivalResponse>
}