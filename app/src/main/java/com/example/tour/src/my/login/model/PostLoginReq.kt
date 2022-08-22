package com.example.tour.src.my.login.model

import com.google.gson.annotations.SerializedName

data class PostLoginReq(
    @SerializedName("email") val email: String,
    @SerializedName("password") val passWord: String
)