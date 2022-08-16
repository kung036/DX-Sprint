package com.example.tour.src.chat.room.model

import com.example.tour.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetRoomChatLog(
    @SerializedName("result") val result : List<GetRoomRes>
):BaseResponse()
