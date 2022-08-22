package com.example.tour.src.crew.make

import com.example.tour.src.crew.make.model.GetFestivalResponse
import com.example.tour.src.crew.make.model.PostCrewReq
import com.example.tour.src.crew.make.model.PostCrewRes

interface MakeCrewActivityInterface {
    fun onPostCrewSuccess(response: PostCrewRes)

    fun onPostCrewFailure(message: String)

    fun onGetFestivalSuccess(response: GetFestivalResponse)

    fun onGetFestivalFailure(message: String)
}