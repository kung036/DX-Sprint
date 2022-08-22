package com.example.tour.src.my.signup.model

import com.google.gson.annotations.SerializedName

data class PostSignUpReq(
    @SerializedName("email") val email: String,
    @SerializedName("passWord") val passWord: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("nickName") val nickName: String

)