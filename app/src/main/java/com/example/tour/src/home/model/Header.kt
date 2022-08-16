package com.example.tour.src.home.model

import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String
)