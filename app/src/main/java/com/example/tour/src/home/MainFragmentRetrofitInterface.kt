package com.example.tour.src.home

import com.example.tour.src.home.model.GetCrewRes
import com.example.tour.src.home.model.GetFestivalResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MainFragmentRetrofitInterface {
    @GET("getFestivalKr")
    fun getFestivals(@Query("serviceKey") serviceKey:String,
                @Query("numOfRows") numOfRows:Int,
                @Query("resultType") resultType:String): Call<GetFestivalResponse>

    @GET("/crews")
    fun getFewCrews(): Call<GetCrewRes>
}