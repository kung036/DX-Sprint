package com.example.tour.src.crew.detail

import com.example.tour.config.ApplicationClass
import com.example.tour.src.crew.detail.model.GetCrewDetailRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrewAttendDetailActivityService(val crewAttendDetailActivityInterface: CrewAttendDetailActivityInterface) {
    fun tryGetCrewDetail(crewIdx : Int){
        val crewDetailFragmentRetrofitInterface = ApplicationClass.sRetrofit.create(
            CrewAttendDetailactivityRetrofitInterface::class.java)
        crewDetailFragmentRetrofitInterface.getCrewDetail(crewIdx).enqueue(object : Callback<GetCrewDetailRes> {
            override fun onResponse(call: Call<GetCrewDetailRes>, response: Response<GetCrewDetailRes>) {
                crewAttendDetailActivityInterface.onGetCrewDetailSuccess(response.body() as GetCrewDetailRes)
            }

            override fun onFailure(call: Call<GetCrewDetailRes>, t: Throwable) {
                crewAttendDetailActivityInterface.onGetCrewDetailFailure(t.message ?: "통신 오류")
            }
        })
    }
}