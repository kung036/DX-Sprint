package com.example.tour.src.crew.crewAttend.model

import com.example.tour.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetCrewFestivalRes(
    @SerializedName("result") val result : List<CrewByFestivalRes>
) : BaseResponse()
