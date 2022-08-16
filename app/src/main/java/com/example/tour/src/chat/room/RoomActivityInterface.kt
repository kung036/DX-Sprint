package com.example.tour.src.chat.room

import com.example.tour.src.chat.room.model.GetChattingRoomUsersList
import com.example.tour.src.chat.room.model.GetRoomChatLog
import com.example.tour.src.chat.room.model.PostMessageRes

interface RoomActivityInterface {
    fun onGetRoomSuccess(response: GetRoomChatLog)

    fun onGetRoomFailure(message: String)

    fun onPostSendSuccess(response: PostMessageRes)

    fun onPostSendFailure(message: String)

    fun onGetUsersSuccess(response: GetChattingRoomUsersList)

    fun onGetUsersFailure(message: String)
}