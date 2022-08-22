package com.example.tour.detail.model

import com.google.gson.annotations.SerializedName

data class ParticipateUser(
    @SerializedName("userIdx") var userIdx : Int,
    @SerializedName("userProfileImageUrl") var userProfileImageUrl : String,
    @SerializedName("userNickName") var userNickName : String
)
