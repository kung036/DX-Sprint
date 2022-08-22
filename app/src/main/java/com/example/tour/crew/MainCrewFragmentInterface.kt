package com.example.tour.crew

import com.example.tour.crew.model.GetCrewRes

interface MainCrewFragmentInterface {
    fun onGetCrewSuccess(response: GetCrewRes)

    fun onGetCrewFailure(message: String)
}