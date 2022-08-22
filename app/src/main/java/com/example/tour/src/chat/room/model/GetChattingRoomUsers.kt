package com.example.tour.src.chat.room.model

import com.google.gson.annotations.SerializedName

data class GetChattingRoomUsers(
    @SerializedName("userIdx") val userIdx : Int,
    @SerializedName("userProfileImageUrl") val userProfileImageUrl : String,
    @SerializedName("userNickName") val userNickName : String
)
