package com.example.tour.src.chat

import com.example.tour.src.chat.model.GetChatRoomList

interface ChatFragmentInterface {
    fun onGetChatListSuccess(response: GetChatRoomList)

    fun onGetChatListFailure(message: String)
}