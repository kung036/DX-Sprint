package com.example.tour.src.crew.detail.model

import com.example.tour.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class PostParticipateCrewRes(
    @SerializedName("result") var result : PostParticipateCrew,
):BaseResponse()
