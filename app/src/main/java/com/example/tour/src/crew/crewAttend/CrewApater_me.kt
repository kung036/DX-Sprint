package com.example.tour.src.crew.crewAttend

import android.graphics.Bitmap
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.tour.databinding.ItemRecycleCrewBinding
import com.example.tour.src.crew.detail.CrewAttendDetailActivity
import com.example.tour.util.ImageURLClass
import java.net.URL
import kotlin.collections.ArrayList

private lateinit var binding: ItemRecycleCrewBinding

class CrewApater_me(
    private val dataSet: ArrayList<CrewFestival>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            ItemRecycleCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

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

    inner class ViewHolder(private val binding: ItemRecycleCrewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CrewFestival) {
            // 이미지 URL을 Bitmap으로 변경
            var image_task: ImageURLClass = ImageURLClass()
            image_task = ImageURLClass().apply {
                url = URL(data.festivalImageUrl)
            }
            var bitmap: Bitmap = image_task.execute().get()

            var titleStr = data.title.split("(")

            binding.crewViewImage.setImageBitmap(bitmap) // String 이미지
            binding.crewName.text = data.crewName
            binding.crewCondition.text = data.crewGender
            binding.crewHeadCount.text = "${data.crewHeadCount - 1}/${data.totalHeadCount}명 참여"
            binding.crewActivityDate.text = data.crewMeetDate
            binding.crewDibsCount.text = data.dibsCount + "명"

            // 터치했을 때
            binding.crewList.setOnClickListener {
                Intent(context, CrewAttendDetailActivity::class.java).apply {
                    putExtra("crewName", data.crewName)
                    putExtra("image_url", data.festivalImageUrl)
                    putExtra("festivalTitle", titleStr[0].toString())
                    putExtra("festival_id", data.festivalIdx)
                    putExtra("crewIdx", data.crewIdx)
                    putExtra("title", data.title)
                    Log.d("shin", "crew : "+data.place)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }

            // 데이터 전달
//                val bundle = Bundle()
//                var crewAttendDetailFragment: Fragment = CrewDetailFragment()
//                bundle.putString("image_url", data.festivalImageUrl)
//                bundle.putString("festivalTitle", titleStr[0].toString())
//                bundle.putInt("festival_id", data.festivalIdx)
//                bundle.putInt("crewIdx", data.crewIdx)
//                crewAttendDetailFragment.arguments = bundle

            // 화면 전환
            //mainActivity.supportFragmentManager.beginTransaction()
            //   .replace(R.id.framelaout_container, crewAttendDetailFragment).commit()
        }
    }
}