package com.example.tour.src.chat

import com.example.tour.config.ApplicationClass
import com.example.tour.src.chat.model.GetChatRoomList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatFragmentService(val chatFragmentInterface: ChatFragmentInterface) {

    fun tryGetChatList(userIdx : Int){
        val chatFragmentRetrofitInterface = ApplicationClass.sRetrofit.create(
            ChatFragmentRetrofitInterface::class.java)
        chatFragmentRetrofitInterface.getChatList(userIdx).enqueue(object : Callback<GetChatRoomList> {
            override fun onResponse(call: Call<GetChatRoomList>, response: Response<GetChatRoomList>) {
                chatFragmentInterface.onGetChatListSuccess(response.body() as GetChatRoomList)
            }

            override fun onFailure(call: Call<GetChatRoomList>, t: Throwable) {
                chatFragmentInterface.onGetChatListFailure(t.message ?: "통신 오류")
            }
        })
    }
}