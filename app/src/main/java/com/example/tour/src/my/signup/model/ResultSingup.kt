package com.example.tour.src.my.signup.model

import com.google.gson.annotations.SerializedName

data class ResultSignUp(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("jwt") val jwt: String
)