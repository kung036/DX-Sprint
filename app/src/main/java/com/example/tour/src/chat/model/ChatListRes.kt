package com.example.tour.src.chat.model

import com.google.gson.annotations.SerializedName

data class ChatListRes(
    @SerializedName("crewIdx") val crewIdx : Int,
    @SerializedName("roomIdx") val roomIdx : Int,
    @SerializedName("festivalImageUrl") val festivalImageUrl : String,
    @SerializedName("crewName") val crewName : String,
    @SerializedName("type") val type : String?,
    @SerializedName("chatContent") val chatContent : String?,
    @SerializedName("updatedAt") val updatedAt : String?
)
