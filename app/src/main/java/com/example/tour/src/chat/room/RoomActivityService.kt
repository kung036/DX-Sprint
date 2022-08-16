package com.example.tour.src.chat.room

import com.example.tour.config.ApplicationClass
import com.example.tour.src.chat.room.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomActivityService(val roomActivityInterface: RoomActivityInterface) {

    fun tryGetRoomChatLog(roomIdx : Int){
        val roomActivityRetrofitInterface = ApplicationClass.sRetrofit.create(
            RoomActivityRetrofitInterface::class.java)
        roomActivityRetrofitInterface.getRoom(roomIdx).enqueue(object : Callback<GetRoomChatLog> {
            override fun onResponse(call: Call<GetRoomChatLog>, response: Response<GetRoomChatLog>) {
                roomActivityInterface.onGetRoomSuccess(response.body() as GetRoomChatLog)
            }
            override fun onFailure(call: Call<GetRoomChatLog>, t: Throwable) {
                roomActivityInterface.onGetRoomFailure(t.message ?: "통신 오류")
            }
        })
    }
    fun tryPostMessage(postMessageReq : PostMessageReq){
        val roomActivityRetrofitInterface = ApplicationClass.sRetrofit.create(
            RoomActivityRetrofitInterface::class.java)
        roomActivityRetrofitInterface.postMessage(postMessageReq).enqueue(object : Callback<PostMessageRes> {
            override fun onResponse(call: Call<PostMessageRes>, response: Response<PostMessageRes>) {
                roomActivityInterface.onPostSendSuccess(response.body() as PostMessageRes)
            }
            override fun onFailure(call: Call<PostMessageRes>, t: Throwable) {
                roomActivityInterface.onPostSendFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetRoomUserList(roomIdx : Int){
        val roomActivityRetrofitInterface = ApplicationClass.sRetrofit.create(
            RoomActivityRetrofitInterface::class.java)
        roomActivityRetrofitInterface.getRoomUsers(roomIdx).enqueue(object : Callback<GetChattingRoomUsersList> {
            override fun onResponse(call: Call<GetChattingRoomUsersList>, response: Response<GetChattingRoomUsersList>) {
                roomActivityInterface.onGetUsersSuccess(response.body() as GetChattingRoomUsersList)
            }
            override fun onFailure(call: Call<GetChattingRoomUsersList>, t: Throwable) {
                roomActivityInterface.onGetUsersFailure(t.message ?: "통신 오류")
            }
        })
    }
}