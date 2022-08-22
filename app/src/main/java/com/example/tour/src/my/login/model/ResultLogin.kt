package com.example.tour.src.my.login.model

import com.google.gson.annotations.SerializedName

data class ResultLogin(
    @SerializedName("userNo") val userNo: Int,
    @SerializedName("jwt") val jwt: String,
    @SerializedName("userNickName") val userNickName: String
)