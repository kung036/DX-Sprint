package com.example.tour.src.crew.make.model

import com.google.gson.annotations.SerializedName

data class PostCrewReq(
    @SerializedName("UC_SEQ") val UC_SEQ: Int,
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("festivalTitle") val festivalTitle: String,
    @SerializedName("crewName") val crewName: String,
    @SerializedName("crewComment") val crewComment: String,
    @SerializedName("crewHeadCount") val crewHeadCount: Int,
    @SerializedName("crewMeetDate") val crewMeetDate: String,
    @SerializedName("crewMeetTime") val crewMeetTime: String,
    @SerializedName("crewGender") val crewGender: String,
    @SerializedName("crewMinAge") val crewMinAge: Int,
    @SerializedName("crewMaxAge") val crewMaxAge: Int
)
