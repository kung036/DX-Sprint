package com.example.tour.src.chat.room

class ChatModel {
    class User(var userIdx: Int, var userProfileImageUrl: String, var userNickName:String)

    class UserInfo(
        var code: Int,
        var user: User
    )

    class Comments(
        var code: String?,
        var comment: Comment
    )

    class Comment(
        var userIdx: Int?,
        var type: String?,
        var chatContent:String?,
        var updatedAt: Any?,
    )
}