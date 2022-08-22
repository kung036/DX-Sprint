package com.example.tour.src.home.allCrew

import com.example.tour.src.home.model.GetCrewRes
import com.example.tour.src.home.model.GetFestivalResponse

interface MainFragmentInterface {
    fun onGetFewCrewSuccess(response: GetCrewRes)

    fun onGetFewCrewFailure(message: String)
}