package com.example.tour.src.crew.detail.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("userIdx") var userIdx : Int,
    @SerializedName("userProfileImageUrl") var userProfileImageUrl : String,
    @SerializedName("totalHeadCount") var totalHeadCount : Int,
    @SerializedName("crewMeetDate") var crewMeetDate : String,
    @SerializedName("crewMeetTime") var crewMeetTime : String,
    @SerializedName("crewGender") var crewGender : String,
    @SerializedName("crewMinAge") var crewMinAge : Int,
    @SerializedName("crewMaxAge") var crewMaxAge : Int,
    @SerializedName("crewComment") var crewComment : String,
    @SerializedName("crewHeadCount") var crewHeadCount : Int,
    @SerializedName("dibsCount") var dibsCount : Int,
    @SerializedName("participateUser") var participateUser : List<ParticipateUser>
)
