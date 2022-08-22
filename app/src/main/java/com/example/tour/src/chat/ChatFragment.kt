package com.example.tour.src.chat

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tour.R
import com.example.tour.config.ApplicationClass.Companion.USER_IDX
import com.example.tour.config.ApplicationClass.Companion.sSharedPreferences
import com.example.tour.config.BaseFragment
import com.example.tour.databinding.FragmentChatBinding
import com.example.tour.src.chat.model.GetChatRoomList
import com.example.tour.src.home.MainActivity

data class ChatList(val crewIdx:Int, val roomIdx:Int, val festivalImageUrl:String,
                    val crewName : String, val type : String,
                    val chatContent : String, val updatedAt : String)

class ChatFragment : BaseFragment<FragmentChatBinding>
    (FragmentChatBinding::bind, R.layout.fragment_chat), ChatFragmentInterface{
    private lateinit var rvAdapter : ChatAdapter
    private var data = arrayListOf<ChatList>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ChatFragmentService(this).tryGetChatList(sSharedPreferences.getInt(USER_IDX,0))
        //editor.putString(X_ACCESS_TOKEN,"eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoxLCJpYXQiOjE2NTk5NzgyMTksImV4cCI6MTY2MTQ0OTQ0OH0.WQd5lQCBrP5zINsd4Q_HW7L4XXQY8mL4Ojd06GUQnbA")
        //editor.putInt(USER_IDX,1)
        //editor.commit()

        rvAdapter = ChatAdapter(data, requireContext())
        binding.chatRoomList.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        binding.chatRoomList.adapter = rvAdapter

    }

    override fun onGetChatListSuccess(response: GetChatRoomList) {
        var size = response.result.size
        when (response.message) {
            "요청에 성공하였습니다." -> {
                var t = ""
                for (i in 0 until size){
                    if(response.result[i].type==null){
                        t = ""
                    }else{
                        t = response.result[i].type.toString()
                    }
                    data.add(
                        ChatList(response.result[i].crewIdx,response.result[i].roomIdx,
                            response.result[i].festivalImageUrl,response.result[i].crewName,
                            t,
                        response.result[i].chatContent.toString(),response.result[i].updatedAt.toString())
                    )
                }
                rvAdapter.notifyDataSetChanged()
                rvAdapter = ChatAdapter(data, context as Activity)
            }
            else -> {
                Toast.makeText(context, response.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onGetChatListFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}