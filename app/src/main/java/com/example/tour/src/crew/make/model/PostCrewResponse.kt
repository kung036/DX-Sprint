package com.example.tour.src.crew.make.model

import com.example.tour.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class PostCrewResponse(
    @SerializedName("result") val result: String
): BaseResponse()
