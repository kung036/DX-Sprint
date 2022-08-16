package com.example.tour.src.home.model

import com.google.gson.annotations.SerializedName

data class GetFestivalKr(
    @SerializedName("header") val header: Header,
    @SerializedName("item") val item: List<Item>,
    @SerializedName("numOfRows") val numOfRows: Int,
    @SerializedName("pageNo") val pageNo: Int,
    @SerializedName("totalCount") val totalCount: Int
)