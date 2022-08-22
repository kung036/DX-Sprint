package com.example.tour.src.my.signup

import com.example.tour.config.ApplicationClass
import com.example.tour.src.my.signup.model.PostSignUpReq
import com.example.tour.src.my.signup.model.PostSignUpRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MySignupService(val mySignupActivityInterface : MySignupActivityInterface) {
    fun tryPostSignUp(postSignUpRequest: PostSignUpReq){
        val mySignupRetrofitInterface = ApplicationClass.sRetrofit.create(MySignupRetrofitInterface::class.java)
        mySignupRetrofitInterface.postSignUp(postSignUpRequest).enqueue(object : Callback<PostSignUpRes> {
            override fun onResponse(call: Call<PostSignUpRes>, response: Response<PostSignUpRes>) {
                mySignupActivityInterface.onPostSignUpSuccess(response.body() as PostSignUpRes)
            }

            override fun onFailure(call: Call<PostSignUpRes>, t: Throwable) {
                mySignupActivityInterface.onPostSignUpFailure(t.message ?: "통신 오류")
            }
        })
    }

}