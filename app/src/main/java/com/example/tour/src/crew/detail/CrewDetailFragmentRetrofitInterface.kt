package com.example.tour.src.crew.detail

import com.example.tour.src.crew.detail.model.GetCrewDetailRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CrewDetailFragmentRetrofitInterface {
    @GET("/crews/detail")
    fun getCrewDetail(@Query("crewIdx") crewIdx : Int): Call<GetCrewDetailRes>
}