package com.example.tour.detail

import com.example.tour.detail.model.GetCrewDetailRes

interface CrewDetailFragmentInterface {
    fun onGetCrewDetailSuccess(response: GetCrewDetailRes)

    fun onGetCrewDetailFailure(message: String)
}