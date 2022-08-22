package com.example.tour.src.test

import com.example.tour.config.ApplicationClass
import com.example.tour.src.test.model.GetCrewFestivalRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrewAttendFragmentService(val crewAttendFragmentInterface: CrewAttendFragmentInterface){

    fun tryGetCrewFestivalDetail(festivalIdx : Int){
        val crewAttendFragmentRetrofitInterface = ApplicationClass.sRetrofit.create(
            CrewAttendFragmentRetrofitInterface::class.java)
        crewAttendFragmentRetrofitInterface.getCrewFestivalDetail(festivalIdx).enqueue(object :
            Callback<GetCrewFestivalRes> {
            override fun onResponse(call: Call<GetCrewFestivalRes>, response: Response<GetCrewFestivalRes>) {
                crewAttendFragmentInterface.onGetCrewByFestivalSuccess(response.body() as GetCrewFestivalRes)
            }

            override fun onFailure(call: Call<GetCrewFestivalRes>, t: Throwable) {
                crewAttendFragmentInterface.onGetCrewByFestivalFailure(t.message ?: "통신 오류")
            }
        })
    }
}