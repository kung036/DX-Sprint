package com.example.tour.src.my.login

import com.example.tour.src.my.login.model.PostLoginRes

interface MyLoginActivityInterface {
    fun onPostLoginSuccess(response: PostLoginRes)

    fun onPostLoginFailure(message: String)
}