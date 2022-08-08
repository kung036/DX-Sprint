package com.example.tour.src

import com.example.tour.R

// 리사이클러 뷰 데이터 추가
private fun addData() {
    val dataSet = arrayListOf<card>()

    for (i in 0 .. 1) {
        dataSet.add(
            card(
                R.drawable.thumbnail_image1_ntime,
                "2022.7.30 토 ~ 2022.08.07 일 (1)",
                "부산바다축제(한, 영, 중간, 중버, 일)",
                "해운대 해수욕장, 광안리 해수욕장, 다대포 해수욕장 등"
            )
        )
        dataSet.add(
            card(
                R.drawable.thumbnail_image1_ntime,
                "2022.7.30 토 ~ 2022.08.07 일 (2)",
                "부산바다축제(한, 영, 중간, 중버, 일)",
                "해운대 해수욕장, 광안리 해수욕장, 다대포 해수욕장 등"
            )
        )
        dataSet.add(
            card(
                R.drawable.thumbnail_image1_ntime,
                "2022.7.30 토 ~ 2022.08.07 일 (3)",
                "부산바다축제(한, 영, 중간, 중버, 일)",
                "해운대 해수욕장, 광안리 해수욕장, 다대포 해수욕장 등"
            )
        )
    }
}