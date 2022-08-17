package com.example.tour.src.crew.crewAttend

import com.example.tour.config.ApplicationClass
import com.example.tour.src.crew.crewAttend.model.GetCrewFestivalRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrewAttendActivityService(val crewAttendActivityInterface: CrewAttendActivityInterface){

    fun tryGetCrewFestivalDetail(festivalIdx : Int){
        val crewAttendFragmentRetrofitInterface = ApplicationClass.sRetrofit.create(
            CrewAttendActivityRetrofitInterface::class.java)
        crewAttendFragmentRetrofitInterface.getCrewFestivalDetail(festivalIdx).enqueue(object :
            Callback<GetCrewFestivalRes> {
            override fun onResponse(call: Call<GetCrewFestivalRes>, response: Response<GetCrewFestivalRes>) {
                crewAttendActivityInterface.onGetCrewByFestivalSuccess(response.body() as GetCrewFestivalRes)
            }

            override fun onFailure(call: Call<GetCrewFestivalRes>, t: Throwable) {
                crewAttendActivityInterface.onGetCrewByFestivalFailure(t.message ?: "통신 오류")
            }
        })
    }
}