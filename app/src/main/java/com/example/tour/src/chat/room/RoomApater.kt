package com.example.tour.src.crew.crewAttend.model


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.example.tour.config.ApplicationClass.Companion.USER_IDX
import com.example.tour.config.ApplicationClass.Companion.sSharedPreferences
import com.example.tour.databinding.*
import com.example.tour.src.chat.room.Room
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private lateinit var binding: ItemChatBubbleBinding
private lateinit var headerbinding:ItemHeaderRoomChatTimeBinding

class RoomAdapter(private val dataSet: ArrayList<Room>, private val con: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_ITEM = 1
    private val TYPE_HEADER = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == TYPE_HEADER){
            headerbinding = ItemHeaderRoomChatTimeBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            return HeaderViewHolder(headerbinding)
        }
        else{
            binding = ItemChatBubbleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind()
            is ViewHolder -> holder.bind(dataSet[position])
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(private val binding: ItemChatBubbleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Room) {
            val format: DateFormat = SimpleDateFormat("KK:mm")
            var str = data.updatedAt.split(" ")
            var time = ""
            if(str[1].substring(0,2).toInt() > 11){
                time = "오후" + str[1].substring(0,5)
            }else{
                time = "오전" + str[1].substring(0,5)
            }
            Glide.with(con).load(data.userProfileImageUrl).into(binding.itemChattingBubbleIvOthersImg);
            binding.othersName.text = data.userNickName
            if(data.userIdx == sSharedPreferences.getInt(USER_IDX,0)){
                binding.itemChattingBubbleCvOthersImg.visibility = View.GONE
                binding.itemChattingBubbleTvOthersChat.visibility = View.GONE
                binding.itemChattingBubbleTvOthersChatTime.visibility = View.GONE
                binding.othersName.visibility = View.GONE
                binding.itemChattingBubbleTvMyChat.text = data.message
                binding.itemChattingBubbleTvChatTime.text = time
            }else{
                if(!data.prev){
                    binding.itemChattingBubbleCvOthersImg.visibility = View.VISIBLE
                    binding.itemChattingBubbleTvOthersChat.visibility = View.VISIBLE
                    binding.itemChattingBubbleTvOthersChatTime.visibility = View.VISIBLE
                    binding.othersName.visibility = View.VISIBLE
                    binding.itemChattingBubbleTvOthersChat.text = data.message
                    binding.itemChattingBubbleTvOthersChatTime.text = time
                    binding.itemChattingBubbleTvMyChat.visibility = View.GONE
                    binding.itemChattingBubbleTvChatTime.visibility = View.GONE
                }else{
                    binding.itemChattingBubbleCvOthersImg.visibility = View.GONE
                    binding.othersName.visibility = View.GONE
                    binding.itemChattingBubbleTvOthersChat.visibility = View.VISIBLE
                    binding.itemChattingBubbleTvOthersChatTime.visibility = View.VISIBLE
                    binding.itemChattingBubbleTvOthersChat.text = data.message
                    binding.itemChattingBubbleTvOthersChatTime.text = time
                    binding.itemChattingBubbleTvMyChat.visibility = View.GONE
                    binding.itemChattingBubbleTvChatTime.visibility = View.GONE
                }
            }
        }
    }
    inner class HeaderViewHolder(private val binding : ItemHeaderRoomChatTimeBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val cal = Calendar.getInstance()
            cal.time = Date()
            val format: DateFormat = SimpleDateFormat("yyyy년 MM월 dd일")
            binding.itemChattingHeaderTime.text = format.format(cal.time)
        }
    }
}