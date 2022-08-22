package com.example.tour.src.home.collect

import com.example.tour.src.home.collect.model.GetFestivalResponse

interface FestivalCollectInterface {
    fun onGetFestivalSuccess(response: GetFestivalResponse)

    fun onGetFestivalFailure(message: String)
}