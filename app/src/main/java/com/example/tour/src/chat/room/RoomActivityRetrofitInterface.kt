package com.example.tour.src.chat.room

import com.example.tour.src.chat.room.model.GetChattingRoomUsersList
import com.example.tour.src.chat.room.model.GetRoomChatLog
import com.example.tour.src.chat.room.model.PostMessageReq
import com.example.tour.src.chat.room.model.PostMessageRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RoomActivityRetrofitInterface {
    @GET("/chats/message/{roomIdx}")
    fun getRoom(@Path("roomIdx") roomIdx : Int): Call<GetRoomChatLog>

    @POST("/chats/message")
    fun postMessage(@Body params : PostMessageReq): Call<PostMessageRes>

    @GET("/chats/user/{roomIdx}")
    fun getRoomUsers(@Path("roomIdx") roomIdx : Int): Call<GetChattingRoomUsersList>

}