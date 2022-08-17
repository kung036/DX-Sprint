package com.example.tour.src.crew.detail.model

import com.google.gson.annotations.SerializedName

data class PostParticipateCrewReq(
    @SerializedName("crewIdx") var crewIdx : Int,
    @SerializedName("roomIdx") var roomIdx : Int,
    @SerializedName("userIdx") var userIdx : Int
)
