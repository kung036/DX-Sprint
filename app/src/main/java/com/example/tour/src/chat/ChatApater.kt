package com.example.tour.src.chat


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import android.content.Intent
import com.bumptech.glide.Glide
import com.example.tour.databinding.ItemChatListBinding
import com.example.tour.src.chat.room.RoomActivity
import com.example.tour.src.home.MainActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//import com.example.tour.src.MyAdapter.HeaderViewHolder

private lateinit var binding: ItemChatListBinding

class ChatAdapter(
    private val dataSet: ArrayList<ChatList>,
    private val con: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemChatListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
//            is HeaderViewHolder -> holder.bind()
            is ViewHolder -> holder.bind(dataSet[position])
        }
//        holder.bind(dataSet[position])

    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(private val binding: ItemChatListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ChatList) {
            val cal = Calendar.getInstance()
            cal.time = Date()
            val yearFormat: DateFormat = SimpleDateFormat("yyyy")
            val monFormat: DateFormat = SimpleDateFormat("M")
            val dateFormat: DateFormat = SimpleDateFormat("d")
            val hourFormat: DateFormat = SimpleDateFormat("H")
            val minFormat: DateFormat = SimpleDateFormat("m")
            var year = yearFormat.format(cal.time)
            var mon = monFormat.format(cal.time)
            var date = dateFormat.format(cal.time)
            var hour = hourFormat.format(cal.time)
            var min = minFormat.format(cal.time)



            Glide.with(con).load(data.festivalImageUrl).into(binding.crewImage)
            binding.crewName.text = data.crewName
            if(data.chatContent.toString().equals("null")){
                binding.lastChatContent.text = ""
            }else{
                binding.lastChatContent.text = data.chatContent
            }


            var now : String
            if(data.updatedAt.equals("")){
                now = ""
            }else{
                //년
                if(year.toInt() - data.updatedAt.substring(0,4).toInt() < 1){
                    System.out.println("년 : " + year.toInt())
                    //월
                    if(mon.toInt() - data.updatedAt.substring(5,7).toInt() < 1){
                        System.out.println("월 : " + mon.toInt())
                        //일
                        if(date.toInt() - data.updatedAt.substring(8,10).toInt() < 1){
                            System.out.println("일 : " + date.toInt())
                            //시
                            if(hour.toInt() - data.updatedAt.substring(11,13).toInt() < 1){
                                System.out.println("시 : " + hour.toInt())
                                //분
                                now = (min.toInt() - data.updatedAt.substring(14,16).toInt()).toString() + "분 전"
                                System.out.println("분 : " + min.toInt())
                            }else{
                                now = (hour.toInt() - data.updatedAt.substring(11,13).toInt()).toString() + "시간 전"
                            }
                        }else{
                            now = (date.toInt() - data.updatedAt.substring(8,10).toInt()).toString() + "일 전"
                        }
                    }else{
                        now = (mon.toInt() - data.updatedAt.substring(5,7).toInt()).toString() + "년 전"
                    }
                }else{
                    System.out.println("년 : " + year.toInt())
                    System.out.println("년(보내는 년) : " + data.updatedAt.substring(0,4).toInt())
                    now = (year.toInt() - data.updatedAt.substring(0,4).toInt()).toString()
                }
            }
            binding.lastChatTime.text = now

            // 터치했을 때
            binding.chattingRoomLlList.setOnClickListener {
                val intent = Intent(con, RoomActivity::class.java)
                intent.putExtra("roomIdx",data.roomIdx)
                con.startActivity(intent)

            }
        }
    }
}