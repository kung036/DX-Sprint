package com.example.tour.src.home.model

import com.google.gson.annotations.SerializedName

data class CrewRes(
    @SerializedName("crewIdx") val crewIdx : Int,
    @SerializedName("festivalIdx") val festivalIdx : Int,
    @SerializedName("festivalImageUrl") val festivalImageUrl : String,
    @SerializedName("title") val title : String,
    @SerializedName("crewName") val crewName : String,
    @SerializedName("crewGender") val crewGender : String,
    @SerializedName("crewHeadCount") val crewHeadCount : Int,
    @SerializedName("totalHeadCount") val totalHeadCount : Int,
    @SerializedName("crewMeetDate") val crewMeetDate : String,
    @SerializedName("dibsCount") val dibsCount : String
)
