package com.example.tour.src.crew

import com.example.tour.src.crew.model.GetCrewRes

interface MainCrewFragmentInterface {
    fun onGetCrewSuccess(response: GetCrewRes)

    fun onGetCrewFailure(message: String)
}