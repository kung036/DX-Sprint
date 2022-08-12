package com.example.tour.src.crew

import com.example.tour.config.ApplicationClass
import com.example.tour.src.crew.model.GetCrewRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainCrewFragmentService(val mainCrewFragmentInterface: MainCrewFragmentInterface) {

    fun tryGetCrews(){
        val mainCrewFragmentRetrofitInterface = ApplicationClass.sRetrofit.create(
            MainCrewFragmentRetrofitInterface::class.java)
        mainCrewFragmentRetrofitInterface.getCrews().enqueue(object : Callback<GetCrewRes> {
            override fun onResponse(call: Call<GetCrewRes>, response: Response<GetCrewRes>) {
                mainCrewFragmentInterface.onGetCrewSuccess(response.body() as GetCrewRes)
            }

            override fun onFailure(call: Call<GetCrewRes>, t: Throwable) {
                mainCrewFragmentInterface.onGetCrewFailure(t.message ?: "통신 오류")
            }
        })
    }
}