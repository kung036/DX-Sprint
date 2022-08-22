package com.example.tour.src.crew.detail.model

import com.google.gson.annotations.SerializedName

data class GetRoomIdx(
    @SerializedName("roomIdx") var roomIdx : Int,
    @SerializedName("updatedAt") var updatedAt : String
)
