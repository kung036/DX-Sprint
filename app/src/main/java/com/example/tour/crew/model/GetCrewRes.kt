package com.example.tour.crew.model

import com.example.tour.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetCrewRes(
    @SerializedName("result") val result : List<CrewRes>
) : BaseResponse()
