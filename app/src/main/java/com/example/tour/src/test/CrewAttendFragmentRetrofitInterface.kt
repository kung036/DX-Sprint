package com.example.tour.src.test

import com.example.tour.detail.model.GetCrewDetailRes
import com.example.tour.src.test.model.GetCrewFestivalRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CrewAttendFragmentRetrofitInterface {
    @GET("/crews/festival/{festivalIdx}")
    fun getCrewFestivalDetail(@Path("festivalIdx") festivalIdx : Int): Call<GetCrewFestivalRes>
}