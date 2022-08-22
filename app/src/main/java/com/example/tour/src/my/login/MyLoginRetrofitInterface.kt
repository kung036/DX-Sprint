package com.example.tour.src.my.login

import com.example.tour.src.my.login.model.PostLoginReq
import com.example.tour.src.my.login.model.PostLoginRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MyLoginRetrofitInterface {
    @POST("/users/email")
    fun postLogin(@Body params: PostLoginReq): Call<PostLoginRes>
}