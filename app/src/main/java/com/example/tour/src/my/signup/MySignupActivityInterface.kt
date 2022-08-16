package com.example.tour.src.my.signup

import com.example.tour.src.my.signup.model.PostSignUpRes

interface MySignupActivityInterface {
    fun onPostSignUpSuccess(response: PostSignUpRes)

    fun onPostSignUpFailure(message: String)
}