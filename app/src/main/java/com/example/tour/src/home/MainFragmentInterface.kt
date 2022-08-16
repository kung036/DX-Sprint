package com.example.tour.src.home

import com.example.tour.src.home.model.GetCrewRes
import com.example.tour.src.home.model.GetFestivalResponse

interface MainFragmentInterface {
    fun onGetFestivalSuccess(response: GetFestivalResponse)

    fun onGetFestivalFailure(message: String)

    fun onGetFewCrewSuccess(response: GetCrewRes)

    fun onGetFewCrewFailure(message: String)
}