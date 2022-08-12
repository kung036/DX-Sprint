package com.example.tour.detail

import com.example.tour.detail.model.GetCrewDetailRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CrewDetailFragmentRetrofitInterface {
    @GET("/crews/detail")
    fun getCrewDetail(@Query("crewIdx") crewIdx : Int): Call<GetCrewDetailRes>
}