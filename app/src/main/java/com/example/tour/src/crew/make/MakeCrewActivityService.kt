package com.example.tour.src.crew.make

import com.example.tour.config.ApplicationClass
import com.example.tour.src.crew.make.model.GetFestivalResponse
import com.example.tour.src.crew.make.model.PostCrewReq
import com.example.tour.src.crew.make.model.PostCrewRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MakeCrewActivityService(val makeCrewActivityInterface: MakeCrewActivityInterface) {

    fun tryGetFestivals(serviceKey:String, numOfRows:Int, resultType:String){
        val makeCrewActivityRetrofitInterface = ApplicationClass.openRetrofit.create(
            MakeCrewActivityRetrofitInterface::class.java)
        makeCrewActivityRetrofitInterface.getFestivals(serviceKey, numOfRows, resultType).
        enqueue(object : Callback<GetFestivalResponse> {
            override fun onResponse(call: Call<GetFestivalResponse>, response: Response<GetFestivalResponse>) {
                makeCrewActivityInterface.onGetFestivalSuccess(response.body() as GetFestivalResponse)
            }

            override fun onFailure(call: Call<GetFestivalResponse>, t: Throwable) {
                makeCrewActivityInterface.onGetFestivalFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPostCrew(postCrewReq: PostCrewReq){
        val makeCrewActivityRetrofitInterface = ApplicationClass.sRetrofit.create(
            MakeCrewActivityRetrofitInterface::class.java)
        makeCrewActivityRetrofitInterface.postCrews(postCrewReq).
        enqueue(object : Callback<PostCrewRes> {
            override fun onResponse(call: Call<PostCrewRes>, response: Response<PostCrewRes>) {
                makeCrewActivityInterface.onPostCrewSuccess(response.body() as PostCrewRes)
            }

            override fun onFailure(call: Call<PostCrewRes>, t: Throwable) {
                makeCrewActivityInterface.onPostCrewFailure(t.message ?: "통신 오류")
            }
        })
    }
}