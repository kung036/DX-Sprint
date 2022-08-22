package com.example.tour.src.test

import com.example.tour.src.test.model.GetCrewFestivalRes

interface CrewAttendFragmentInterface {
    fun onGetCrewByFestivalSuccess(response: GetCrewFestivalRes)

    fun onGetCrewByFestivalFailure(message: String)
}