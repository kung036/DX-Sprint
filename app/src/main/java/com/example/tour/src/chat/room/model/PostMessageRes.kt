package com.example.tour.src.chat.room.model

import com.example.tour.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class PostMessageRes(
    @SerializedName("result") val result : String
):BaseResponse()
