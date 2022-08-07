package com.example.template.config

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    // 반복되는 리스폰스 내용 중복을 줄이기 위해 사용. 리스폰스 데이터 클래스를 만들때 상속해서 사용.
    @SerializedName("isSuccess") val isSuccess: Boolean = false,
    @SerializedName("code") val code: Int = 0,
    @SerializedName("message") val message: String? = null
)