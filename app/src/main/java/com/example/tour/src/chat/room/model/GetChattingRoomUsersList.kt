package com.example.tour.src.chat.room.model

import com.example.tour.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetChattingRoomUsersList(
    @SerializedName("result") val result : List<GetChattingRoomUsers>
):BaseResponse()
