package com.example.tour.src.chat.room

class RoomDTO {
    private var roomIdx: Int? = null
    private var userIdx: Int? = null
    private var festivalImageUrl: String? = null
    private var type: String? = null
    private var chatContent: String? = null
    private var updatedAt: String? = null

    fun ChatDTO() {}

    fun ChatDTO(roomIdx: Int?, userIdx: Int?, festivalImageUrl: String?, type: String?, chatContent: String?,
                updatedAt: String?) {
        this.roomIdx = roomIdx
        this.userIdx = userIdx
        this.festivalImageUrl = festivalImageUrl
        this.type = type
        this.chatContent = chatContent
        this.updatedAt = updatedAt
    }

    fun setRoomIdx(roomIdx: Int?) {
        this.roomIdx = roomIdx
    }

    fun setUserIdx(userIdx: Int?) {
        this.userIdx = userIdx
    }
    fun setFestivalImageUrl(festivalImageUrl: String?) {
        this.festivalImageUrl = festivalImageUrl
    }

    fun setType(type: String?) {
        this.type = type
    }
    fun setChatContent(chatContent: String?) {
        this.chatContent = chatContent
    }

    fun setUpdatedAt(updatedAt: String?) {
        this.updatedAt = updatedAt
    }

    fun getRoomIdx(): Int? {
        return roomIdx
    }

    fun getUserIdx(): Int? {
        return userIdx
    }

    fun getFestivalImageUrl(): String? {
        return festivalImageUrl
    }

    fun getType(): String? {
        return type
    }
    fun getChatContent(): String? {
        return chatContent
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

}