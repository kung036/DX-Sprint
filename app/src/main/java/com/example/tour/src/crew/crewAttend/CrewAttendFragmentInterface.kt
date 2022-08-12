package com.example.tour.src.crew.crewAttend

import com.example.tour.src.crew.crewAttend.model.GetCrewFestivalRes

interface CrewAttendFragmentInterface {
    fun onGetCrewByFestivalSuccess(response: GetCrewFestivalRes)

    fun onGetCrewByFestivalFailure(message: String)
}