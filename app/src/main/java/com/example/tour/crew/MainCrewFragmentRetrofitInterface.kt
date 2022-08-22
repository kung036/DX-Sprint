package com.example.tour.crew

import com.example.tour.crew.model.GetCrewRes
import retrofit2.Call
import retrofit2.http.GET

interface MainCrewFragmentRetrofitInterface {
    @GET("/crews")
    fun getCrews(): Call<GetCrewRes>
}