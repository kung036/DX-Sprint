package com.example.tour.src.my.login

import com.example.tour.config.ApplicationClass
import com.example.tour.src.my.login.model.PostLoginReq
import com.example.tour.src.my.login.model.PostLoginRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyLoginService(val myLoginActivityInterface : MyLoginActivityInterface) {
    fun tryPostLogin(postLoginRequest: PostLoginReq){
        val myLoginRetrofitInterface = ApplicationClass.sRetrofit.create(MyLoginRetrofitInterface::class.java)
        myLoginRetrofitInterface.postLogin(postLoginRequest).enqueue(object : Callback<PostLoginRes> {
            override fun onResponse(call: Call<PostLoginRes>, response: Response<PostLoginRes>) {
                myLoginActivityInterface.onPostLoginSuccess(response.body() as PostLoginRes)
            }

            override fun onFailure(call: Call<PostLoginRes>, t: Throwable) {
                myLoginActivityInterface.onPostLoginFailure(t.message ?: "통신 오류")
            }
        })
    }

}