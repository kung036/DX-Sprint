package com.example.tour.src.chat.room.model

import com.google.gson.annotations.SerializedName

data class GetRoomRes(
    @SerializedName("roomIdx") val roomIdx : Int,
    @SerializedName("userIdx") val userIdx : Int,
    @SerializedName("userProfileImageUrl") val userProfileImageUrl : String,
    @SerializedName("userNickName") val userNickName : String,
    @SerializedName("type") val type : String,
    @SerializedName("chatContent") val chatContent : String,
    @SerializedName("updatedAt") val updatedAt : String
)
