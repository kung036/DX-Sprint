package com.example.tour.src.crew.detail

import com.example.tour.src.crew.detail.model.GetCrewDetailRes
import com.example.tour.src.crew.detail.model.GetRoomIdxRes
import com.example.tour.src.crew.detail.model.PostParticipateCrewReq
import com.example.tour.src.crew.detail.model.PostParticipateCrewRes
import retrofit2.Call
import retrofit2.http.*

interface CrewAttendDetailactivityRetrofitInterface {
    @GET("/crews/detail")
    fun getCrewDetail(@Query("crewIdx") crewIdx : Int): Call<GetCrewDetailRes>

    @POST("/crews/participate")
    fun postParticipateCrew(@Body params : PostParticipateCrewReq): Call<PostParticipateCrewRes>

    @GET("/crews/room/{userIdx}/{crewIdx}")
    fun getRoomIdx(@Path("userIdx") userIdx : Int,
                   @Path("crewIdx") crewIdx : Int): Call<GetRoomIdxRes>
}