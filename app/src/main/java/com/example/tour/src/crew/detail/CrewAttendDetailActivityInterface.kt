package com.example.tour.src.crew.detail

import com.example.tour.src.crew.detail.model.GetCrewDetailRes

interface CrewAttendDetailActivityInterface {
    fun onGetCrewDetailSuccess(response: GetCrewDetailRes)

    fun onGetCrewDetailFailure(message: String)
}