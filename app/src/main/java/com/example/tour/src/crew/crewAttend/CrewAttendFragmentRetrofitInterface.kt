package com.example.tour.src.crew.crewAttend

import com.example.tour.src.crew.crewAttend.model.GetCrewFestivalRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CrewAttendFragmentRetrofitInterface {
    @GET("/crews/festival/{festivalIdx}")
    fun getCrewFestivalDetail(@Path("festivalIdx") festivalIdx : Int): Call<GetCrewFestivalRes>


}