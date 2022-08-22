package com.example.tour.src.crew.detail

import com.example.tour.src.crew.detail.model.GetCrewDetailRes
import com.example.tour.src.crew.detail.model.GetRoomIdxRes
import com.example.tour.src.crew.detail.model.PostParticipateCrewRes

interface CrewAttendDetailActivityInterface {
    fun onGetCrewDetailSuccess(response: GetCrewDetailRes)

    fun onGetCrewDetailFailure(message: String)

    fun onPostParticipateCrewSuccess(response: PostParticipateCrewRes)

    fun onPostParticipateCrewFailure(message: String)

    fun onGetCrewRoomIdxSuccess(response: GetRoomIdxRes)

    fun onGetCrewRoomIdxFailure(message: String)
}