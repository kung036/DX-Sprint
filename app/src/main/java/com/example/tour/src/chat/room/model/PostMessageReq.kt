package com.example.tour.src.chat.room.model

import com.google.gson.annotations.SerializedName

data class PostMessageReq(
    @SerializedName("roomIdx") val roomIdx : Int,
    @SerializedName("userIdx") val userIdx : Int,
    @SerializedName("type") val type : String,
    @SerializedName("content") val content : String
)
