package com.example.tour.src.crew.detail.model

import com.example.tour.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetCrewDetailRes(
    @SerializedName("result") val result : Result
): BaseResponse()
