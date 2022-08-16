package com.example.tour.src.home.model

import com.example.tour.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetCrewRes(
    @SerializedName("result") val result : List<CrewRes>
) : BaseResponse()
