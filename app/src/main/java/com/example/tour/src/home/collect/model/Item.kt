package com.example.tour.src.home.collect.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("ADDR1") val ADDR1: String,
    @SerializedName("ADDR2") val ADDR2: String,
    @SerializedName("CNTCT_TEL") val CNTCT_TEL: String,
    @SerializedName("GUGUN_NM") val GUGUN_NM: String,
    @SerializedName("HOMEPAGE_URL") val HOMEPAGE_URL: String,
    @SerializedName("ITEMCNTNTS") val ITEMCNTNTS: String,
    @SerializedName("LAT") val LAT: Double,
    @SerializedName("LNG") val LNG: Double,
    @SerializedName("MAIN_IMG_NORMAL") val MAIN_IMG_NORMAL: String,
    @SerializedName("MAIN_IMG_THUMB") val MAIN_IMG_THUMB: String,
    @SerializedName("MAIN_PLACE") val MAIN_PLACE: String,
    @SerializedName("MAIN_TITLE") val MAIN_TITLE: String,
    @SerializedName("MIDDLE_SIZE_RM1") val MIDDLE_SIZE_RM1: String,
    @SerializedName("PLACE") val PLACE: String,
    @SerializedName("SUBTITLE") val SUBTITLE: String,
    @SerializedName("TITLE") val TITLE: String,
    @SerializedName("TRFC_INFO") val TRFC_INFO: String,
    @SerializedName("UC_SEQ") val UC_SEQ: Int,
    @SerializedName("USAGE_AMOUNT") val USAGE_AMOUNT: String,
    @SerializedName("USAGE_DAY") val USAGE_DAY: String,
    @SerializedName("USAGE_DAY_WEEK_AND_TIME") val USAGE_DAY_WEEK_AND_TIME: String
)