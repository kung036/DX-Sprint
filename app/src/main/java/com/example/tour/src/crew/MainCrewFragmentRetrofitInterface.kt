package com.example.tour.src.crew

import com.example.tour.src.crew.model.GetCrewRes
import retrofit2.Call
import retrofit2.http.GET

interface MainCrewFragmentRetrofitInterface {
    @GET("/crews")
    fun getCrews(): Call<GetCrewRes>
}