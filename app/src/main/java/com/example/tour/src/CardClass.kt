package com.example.tour.src

import org.json.JSONObject

data class CardClass (
    val image: String?,
    val date: String?,
    val title: String?,
    val place: String?,

    val address:String?,
    val phoneNumber:String?,
    val homepageURL:String?,
    val traffic:String?,
    val time:String?,
    val money:String?,
    val content:String?,
    val facility:String?,

    val item: JSONObject

)

