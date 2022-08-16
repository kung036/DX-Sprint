package com.example.tour.src.my.signup

import com.example.tour.src.my.signup.model.PostSignUpReq
import com.example.tour.src.my.signup.model.PostSignUpRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MySignupRetrofitInterface {
    @POST("/users")
    fun postSignUp(@Body params: PostSignUpReq): Call<PostSignUpRes>
}