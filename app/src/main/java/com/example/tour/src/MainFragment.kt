package com.example.tour.src

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tour.R
import com.example.tour.config.BaseFragment
import com.example.tour.databinding.ActivityMainBinding
import com.example.tour.databinding.FragmentMainBinding

private lateinit var binding: FragmentMainBinding

data class card (val main_view_image: Int, val main_view_date: String, val main_view_title: String, val main_view_place: String)

//class MainFragment : Fragment() {
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::bind,
    R.layout.fragment_main){
    private val dataSet = arrayListOf<card>()
    private lateinit var rvAdapter: MyAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        // 리사이클러 뷰
        addData() // 데이터추가
        rvAdapter = MyAdapter(dataSet)
        binding.mainViewFestival.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.mainViewFestival.adapter = rvAdapter
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT
        ){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPos: Int = viewHolder.adapterPosition
                val toPos: Int = target.adapterPosition
                rvAdapter.swapData(fromPos, toPos)
                return true
            }

            //스와이프시 데이터 삭제
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                rvAdapter.removeData(viewHolder.layoutPosition)
            }

//            override fun onChildDraw(
//                c: Canvas,
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                dX: Float,
//                dY: Float,
//                actionState: Int,
//                isCurrentlyActive: Boolean
//            ) {
//                val icon: Bitmap
//                // actionState가 SWIPE 동작일 때 배경을 빨간색으로 칠하는 작업을 수행하도록 함
//                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//                    val itemView = viewHolder.itemView
//                    val height = (itemView.bottom - itemView.top).toFloat()
//                    val width = height / 4
//                    val paint = Paint()
//                    if (dX < 0) {  // 왼쪽으로 스와이프하는지 확인
//                        // 뷰홀더의 백그라운드에 깔아줄 사각형의 크기와 색상을 지정
//                        paint.color = Color.parseColor("#ff0000")
//                        val background = RectF(
//                            itemView.right.toFloat() + dX,
//                            itemView.top.toFloat(),
//                            itemView.right.toFloat(),
//                            itemView.bottom.toFloat()
//                        )
//                        c.drawRect(background, paint)
//
//                        // 휴지통 아이콘과 표시될 위치를 지정하고 비트맵을 그려줌
//                        // 비트맵 이미지는 Image Asset 기능으로 추가하고 drawable 폴더에 위치하도록 함
//                        icon = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background)
//                        val iconDst = RectF(
//                            itemView.left.toFloat() - 3 + width * 4,
//                            (itemView.top.toFloat() + width * 1.5).toFloat(),
//                            itemView.right.toFloat() + width - width,
//                            (itemView.bottom.toFloat() - width * 1.3).toFloat()
//                        )
//                        c.drawBitmap(icon, null, iconDst, null)
//                    }
//                }
//
//                super.onChildDraw(
//                    c,
//                    recyclerView,
//                    viewHolder,
//                    dX,
//                    dY,
//                    actionState,
//                    isCurrentlyActive
//                )
//            }
        }
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.mainViewFestival)
    }

    // 리사이클러 뷰 데이터 추가
    private fun addData() {
        for (i in 0 .. 1) {
            dataSet.add(
                card(
                    com.example.tour.R.drawable.thumbnail_image1_ntime,
                    "2022.7.30 토 ~ 2022.08.07 일 (1)",
                    "부산바다축제(한, 영, 중간, 중버, 일)",
                    "해운대 해수욕장, 광안리 해수욕장, 다대포 해수욕장 등"
                )
            )
            dataSet.add(
                card(
                    com.example.tour.R.drawable.thumbnail_image1_ntime,
                    "2022.7.30 토 ~ 2022.08.07 일 (2)",
                    "부산바다축제(한, 영, 중간, 중버, 일)",
                    "해운대 해수욕장, 광안리 해수욕장, 다대포 해수욕장 등"
                )
            )
            dataSet.add(
                card(
                    com.example.tour.R.drawable.thumbnail_image1_ntime,
                    "2022.7.30 토 ~ 2022.08.07 일 (3)",
                    "부산바다축제(한, 영, 중간, 중버, 일)",
                    "해운대 해수욕장, 광안리 해수욕장, 다대포 해수욕장 등"
                )
            )
        }
    }
}