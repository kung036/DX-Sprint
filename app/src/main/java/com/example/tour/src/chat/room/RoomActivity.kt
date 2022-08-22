package com.example.tour.src.chat.room

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ScrollView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tour.config.ApplicationClass
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.ActivityChatRoomBinding
import com.example.tour.src.chat.room.model.GetChattingRoomUsersList
import com.example.tour.src.chat.room.model.GetRoomChatLog
import com.example.tour.src.chat.room.model.PostMessageReq
import com.example.tour.src.chat.room.model.PostMessageRes
import com.example.tour.src.crew.crewAttend.model.RoomAdapter
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


data class Room(val roomIdx:Int, val userIdx:Int, val userProfileImageUrl:String, val userNickName:String,
                val type : String, val message : String, val updatedAt : String, val prev : Boolean)
class RoomActivity : BaseActivity<ActivityChatRoomBinding>(ActivityChatRoomBinding::inflate),
    RoomActivityInterface {
    private lateinit var rvAdapter : RoomAdapter
    private var data = arrayListOf<Room>()
    private var roomIdx:Int = 0
    //private lateinit var keyboardVisibilityUtils: KeyboardVisibilityUtils
    private var scroll : ScrollView? = null
    private val firebaseDatabase: FirebaseDatabase? = null
    var database = FirebaseDatabase.getInstance()
    var databaseReference = database.getReference("message")

    private var myIdx: Int = 0
    private lateinit var userProfileImageUrl: String
    private lateinit var userNickName: String
    private var userDataSet = arrayListOf<ChatModel.User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var roomIntent = intent
        Log.d("test",roomIntent.getIntExtra("roomIdx",0).toString())
        roomIdx = roomIntent.getIntExtra("roomIdx",0)

        RoomActivityService(this).tryGetRoomUserList(roomIdx)
        rvAdapter = RoomAdapter(data,this)
        binding.rvChatList.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        binding.rvChatList.adapter = rvAdapter
        RoomActivityService(this).tryGetRoomChatLog(roomIdx)

        binding.chattingRoomIbSend.setOnClickListener {
            if(binding.chattingRoomEtChat.text.isNotEmpty()){
                var message = binding.chattingRoomEtChat.text
                RoomActivityService(this).
                tryPostMessage(PostMessageReq(roomIdx = roomIdx, userIdx = ApplicationClass.sSharedPreferences.getInt(
                    ApplicationClass.USER_IDX,0), type = "text", content = message.toString()))
                //sendMsg()
                binding.chattingRoomEtChat.text = null
                //RoomActivityService(this).tryGetRoomChatLog(roomIdx)
            }
        }

//        binding.chattingRoomEtChat.setOnClickListener {
//            binding.svRoot.fullScroll(ScrollView.FOCUS_DOWN)
//            scrollDown()
//            val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            inputMethodManager.showSoftInput(binding.chattingRoomEtChat, 0)
//        }
//        keyboardVisibilityUtils = KeyboardVisibilityUtils(window,
//            onShowKeyboard = { keyboardHeight ->
//                binding.svRoot.run {
//                    smoothScrollTo(scrollX, scrollY + keyboardHeight)
//                }
//            })

    }
//    private fun scrollDown(){
//        binding.svRoot.post(Runnable {
//            @Override
//            fun run() {
//                binding.svRoot.fullScroll(ScrollView.FOCUS_DOWN);
//            }
//        })
//    }

    private fun sendMsg() {
        binding.chattingRoomIbSend.setOnClickListener {
            val chatModel = ChatModel()
            var myuid : Int = 0
            for(i in 0 until userDataSet.size){
                ChatModel.UserInfo(userDataSet[i].userIdx, userDataSet[i])
            }

            //push() 데이터가 쌓이기 위해 채팅방 key가 생성
            if (roomIdx == null) {
                //Toast.makeText(this@MessageActivity, "채팅방 생성", Toast.LENGTH_SHORT).show()
                //button.setEnabled(false)
                firebaseDatabase?.reference?.child("chatrooms")?.push()?.setValue(chatModel)
                    ?.addOnSuccessListener(
                        OnSuccessListener<Void?> {
                            //checkChatRoom()
                        })
            } else {
                sendMsgToDataBase()
            }
        }
    }
    var chatIdx = 1
    private fun sendMsgToDataBase() {
        if (!binding.chattingRoomEtChat.text.toString().equals("")) {
            var date = Date()
            var format = SimpleDateFormat("KK:mm")
            var now = format.format(date)

            val comment = ChatModel.Comment(ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_IDX,0),
                binding.chattingRoomEtChat.text.toString(),now,"text")
            val comments = ChatModel.Comments(chatIdx.toString(),comment)
            firebaseDatabase!!.reference.child("chatRooms").child(roomIdx.toString()).child("chatIdx")
                .push().setValue(comments).addOnSuccessListener {
                    binding.chattingRoomEtChat.setText("")
                }
            chatIdx++
        }
    }

    /*private fun checkChatRoom() {
        //자신 key == true 일때 chatModel 가져온다.
        /* chatModel
        public Map<String,Boolean> users = new HashMap<>(); //채팅방 유저
        public Map<String, ChatModel.Comment> comments = new HashMap<>(); //채팅 메시지
        */
        firebaseDatabase.getReference().child("chatrooms").orderByChild("users/$myuid")
            .equalTo(true).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataSnapshot in snapshot.children)  //나, 상대방 id 가져온다.
                    {
                        val chatModel = dataSnapshot.getValue(ChatModel::class.java)
                        if (chatModel!!.users.containsKey(destUid)) {           //상대방 id 포함돼 있을때 채팅방 key 가져옴
                            chatRoomUid = dataSnapshot.key
                            button.setEnabled(true)

                            //동기화
                            //recyclerView.setLayoutManager(LinearLayoutManager(this@MessageActivity))
                            //recyclerView.setAdapter(RecyclerViewAdapter())

                            //메시지 보내기
                            sendMsgToDataBase()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }*/

    @SuppressLint("NotifyDataSetChanged")
    override fun onGetRoomSuccess(response: GetRoomChatLog) {
        var size = response.result.size
        when (response.message) {
            "요청에 성공하였습니다." -> {
                //for(a in 0 until 3) {
                for (i in 0 until size + 1) {
                    if (i == 0) {
                        data.add(
                            Room(
                                0,
                                0,
                                "",
                                "",
                                "",
                                "",
                                "",
                                false
                            )
                        )
                    } else if (i == 1) {
                        data.add(
                            Room(
                                response.result[i - 1].roomIdx,
                                response.result[i - 1].userIdx,
                                response.result[i - 1].userProfileImageUrl,
                                response.result[i - 1].userNickName,
                                response.result[i - 1].type.toString(),
                                response.result[i - 1].chatContent,
                                response.result[i - 1].updatedAt,
                                false
                            )
                        )
                    } else {
                        if (response.result[i - 1].userIdx == response.result[i - 2].userIdx) {
                            data.add(
                                Room(
                                    response.result[i - 1].roomIdx,
                                    response.result[i - 1].userIdx,
                                    response.result[i - 1].userProfileImageUrl,
                                    response.result[i - 1].userNickName,
                                    response.result[i - 1].type.toString(),
                                    response.result[i - 1].chatContent,
                                    response.result[i - 1].updatedAt,
                                    true
                                )
                            )
                        } else {
                            data.add(
                                Room(
                                    response.result[i - 1].roomIdx,
                                    response.result[i - 1].userIdx,
                                    response.result[i - 1].userProfileImageUrl,
                                    response.result[i - 1].userNickName,
                                    response.result[i - 1].type.toString(),
                                    response.result[i - 1].chatContent,
                                    response.result[i - 1].updatedAt,
                                    false
                                )
                            )
                        }
                    }

                }
                //}
                rvAdapter.notifyDataSetChanged()
                rvAdapter = RoomAdapter(data, this)
                binding.rvChatList.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL,false)
                binding.rvChatList.adapter = rvAdapter
            }
            else -> {
                showCustomToast(response.message.toString())
            }
        }
    }

    override fun onGetRoomFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onPostSendSuccess(response: PostMessageRes) {
        when (response.message) {
            "요청에 성공하였습니다." -> {
                var emptyData = arrayListOf<Room>()
                data = emptyData
                rvAdapter = RoomAdapter(data,this)
                rvAdapter.notifyDataSetChanged()
                RoomActivityService(this).tryGetRoomChatLog(roomIdx)
                binding.rvChatList.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL,false)
                binding.rvChatList.adapter = rvAdapter
            }
        }
    }

    override fun onPostSendFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onGetUsersSuccess(response: GetChattingRoomUsersList) {
        var size = response.result.size
        when (response.message) {
            "요청에 성공하였습니다." -> {
                userDataSet = arrayListOf<ChatModel.User>()
                for(i in 0 until size){
                    myIdx = response.result[i].userIdx
                    userProfileImageUrl = response.result[i].userProfileImageUrl
                    userNickName = response.result[i].userNickName
                    userDataSet.add(ChatModel.User(myIdx,userProfileImageUrl,userNickName))
                }
            }
        }
    }

    override fun onGetUsersFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}