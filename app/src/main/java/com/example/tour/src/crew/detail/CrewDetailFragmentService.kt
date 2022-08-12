package com.example.tour.src.crew.detail

import com.example.tour.config.ApplicationClass
import com.example.tour.src.crew.detail.model.GetCrewDetailRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrewDetailFragmentService(val crewDetailFragmentInterface: CrewDetailFragmentInterface) {
    fun tryGetCrewDetail(crewIdx : Int){
        val crewDetailFragmentRetrofitInterface = ApplicationClass.sRetrofit.create(
            CrewDetailFragmentRetrofitInterface::class.java)
        crewDetailFragmentRetrofitInterface.getCrewDetail(crewIdx).enqueue(object : Callback<GetCrewDetailRes> {
            override fun onResponse(call: Call<GetCrewDetailRes>, response: Response<GetCrewDetailRes>) {
                crewDetailFragmentInterface.onGetCrewDetailSuccess(response.body() as GetCrewDetailRes)
            }

            override fun onFailure(call: Call<GetCrewDetailRes>, t: Throwable) {
                crewDetailFragmentInterface.onGetCrewDetailFailure(t.message ?: "통신 오류")
            }
        })
    }
}