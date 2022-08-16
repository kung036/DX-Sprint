package com.example.tour.src.chat.model

import com.example.tour.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetChatRoomList(
    @SerializedName("result") val result : List<ChatListRes>
):BaseResponse()
