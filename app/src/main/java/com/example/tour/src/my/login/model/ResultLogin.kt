package com.example.tour.src.my.login.model

import com.google.gson.annotations.SerializedName

data class ResultLogin(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("jwt") val jwt: String
)