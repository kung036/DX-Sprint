package com.example.tour.src.crew.detail.model

import com.example.tour.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetRoomIdxRes(
    @SerializedName("result") var result : GetRoomIdx
):BaseResponse()
