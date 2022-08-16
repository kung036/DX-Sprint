package com.example.tour.src.home.collect.model

import com.google.gson.annotations.SerializedName

data class GetFestivalResponse(
    @SerializedName("getFestivalKr") val getFestivalKr: GetFestivalKr
)