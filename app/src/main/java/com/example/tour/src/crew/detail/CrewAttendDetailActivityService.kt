package com.example.tour.src.crew.detail

import com.example.tour.config.ApplicationClass
import com.example.tour.src.crew.detail.model.GetCrewDetailRes
import com.example.tour.src.crew.detail.model.GetRoomIdxRes
import com.example.tour.src.crew.detail.model.PostParticipateCrewReq
import com.example.tour.src.crew.detail.model.PostParticipateCrewRes
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

    fun tryPostParticipateCrew(postParticipateCrewReq: PostParticipateCrewReq){
        val crewDetailFragmentRetrofitInterface = ApplicationClass.sRetrofit.create(
            CrewAttendDetailactivityRetrofitInterface::class.java)
        crewDetailFragmentRetrofitInterface.postParticipateCrew(postParticipateCrewReq).enqueue(object : Callback<PostParticipateCrewRes> {
            override fun onResponse(call: Call<PostParticipateCrewRes>, response: Response<PostParticipateCrewRes>) {
                crewAttendDetailActivityInterface.onPostParticipateCrewSuccess(response.body() as PostParticipateCrewRes)
            }

            override fun onFailure(call: Call<PostParticipateCrewRes>, t: Throwable) {
                crewAttendDetailActivityInterface.onPostParticipateCrewFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetCrewRoomIdx(userIdx:Int, crewIdx : Int){
        val crewDetailFragmentRetrofitInterface = ApplicationClass.sRetrofit.create(
            CrewAttendDetailactivityRetrofitInterface::class.java)
        crewDetailFragmentRetrofitInterface.getRoomIdx(userIdx, crewIdx).enqueue(object : Callback<GetRoomIdxRes> {
            override fun onResponse(call: Call<GetRoomIdxRes>, response: Response<GetRoomIdxRes>) {
                crewAttendDetailActivityInterface.onGetCrewRoomIdxSuccess(response.body() as GetRoomIdxRes)
            }

            override fun onFailure(call: Call<GetRoomIdxRes>, t: Throwable) {
                crewAttendDetailActivityInterface.onGetCrewRoomIdxFailure(t.message ?: "통신 오류")
            }
        })
    }
}