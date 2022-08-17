package com.example.tour.src.crew.make

import com.example.tour.src.crew.make.model.GetFestivalResponse
import com.example.tour.src.crew.make.model.PostCrewReq
import com.example.tour.src.crew.make.model.PostCrewRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MakeCrewActivityRetrofitInterface {
    @POST("/crews")
    fun postCrews(@Body params:PostCrewReq): Call<PostCrewRes>

    @GET("getFestivalKr")
    fun getFestivals(@Query("serviceKey") serviceKey:String,
                 @Query("numOfRows") numOfRows:Int,
                 @Query("resultType") resultType:String): Call<GetFestivalResponse>
}