package com.example.tour.src.chat

import com.example.tour.src.chat.model.GetChatRoomList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatFragmentRetrofitInterface {
    @GET("/chats/room/{userIdx}")
    fun getChatList(@Path("userIdx") userIdx : Int): Call<GetChatRoomList>
}