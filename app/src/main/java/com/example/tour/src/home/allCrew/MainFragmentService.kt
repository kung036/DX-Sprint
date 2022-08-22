package com.example.tour.src.home.allCrew

import com.example.tour.config.ApplicationClass
import com.example.tour.src.home.model.GetCrewRes
import com.example.tour.src.home.model.GetFestivalResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragmentService(val mainFragmentInterface: MainFragmentInterface) {
    fun tryGetFewCrews(){
        val mainFragmentRetrofitInterface = ApplicationClass.sRetrofit.create(
            MainFragmentRetrofitInterface::class.java)
        mainFragmentRetrofitInterface.getFewCrews().enqueue(object : Callback<GetCrewRes> {
            override fun onResponse(call: Call<GetCrewRes>, response: Response<GetCrewRes>) {
                mainFragmentInterface.onGetFewCrewSuccess(response.body() as GetCrewRes)
            }

            override fun onFailure(call: Call<GetCrewRes>, t: Throwable) {
                mainFragmentInterface.onGetFewCrewFailure(t.message ?: "통신 오류")
            }
        })
    }
}