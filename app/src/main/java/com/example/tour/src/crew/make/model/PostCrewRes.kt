package com.example.tour.src.crew.make.model

import com.example.tour.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class PostCrewRes(
    @SerializedName("result") val result: PostCrewResponse
): BaseResponse()
