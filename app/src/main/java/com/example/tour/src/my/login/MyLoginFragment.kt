package com.example.tour.src.my.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tour.R
import com.example.tour.config.ApplicationClass
import com.example.tour.config.ApplicationClass.Companion.NICKNAME_TOKEN
import com.example.tour.config.BaseFragment
import com.example.tour.databinding.FragmentMyLoginBinding
import com.example.tour.src.home.MainActivity
import com.example.tour.src.my.MyFragment
import com.example.tour.src.my.login.model.PostLoginReq
import com.example.tour.src.my.login.model.PostLoginRes
import com.example.tour.src.my.signup.MySignupFragment

//private lateinit var binding: FragmentMySignupBinding

class MyLoginFragment : BaseFragment<FragmentMyLoginBinding>
    (FragmentMyLoginBinding::bind, R.layout.fragment_my_login),
    MyLoginFragmentInterface {

    // fragment에서 runOnithread 사용하기
    lateinit var mainActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButtonSignup.setOnClickListener {
            mainActivity.supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MySignupFragment()).commit()
        }

        binding.loginButton.setOnClickListener {
            var id = binding.loginId.text.toString()
            var pw = binding.loginPw.text.toString()

            // 서버 전달
            val postRequest = PostLoginReq(id, pw)
//            showLoadingDialog(context)
            MyLoginService(this).tryPostLogin(postRequest)
        }
    }

    override fun onPostLoginSuccess(response: PostLoginRes) {
//        dismissLoadingDialog()
        when (response.message) {
            "요청에 성공하였습니다." -> {
                showCustomToast("로그인 성공")

//                ApplicationClass.editor.putString(ApplicationClass.NICKNAME_TOKEN, response.result.userIdx.toString())
                ApplicationClass.editor.putString(NICKNAME_TOKEN, "닉네임!!")
                ApplicationClass.editor.commit()
                ApplicationClass.editor.putString(ApplicationClass.X_ACCESS_TOKEN, response.result.jwt)
                ApplicationClass.editor.putInt(ApplicationClass.USER_IDX, response.result.userIdx)
                ApplicationClass.editor.commit()
                Log.d("jwt",
                    ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN,"").toString()
                )
//                var nickname = ApplicationClass.sSharedPreferences.getString(NICKNAME_TOKEN, "EMPTY")
//                Log.d("shin", "${nickname}")
//                ApplicationClass.editor.commit()

                // 화면 전환
                mainActivity.supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MyFragment()).commit()
            }
            else -> {
                showCustomToast(response.message.toString())
            }
        }
    }

    override fun onPostLoginFailure(message: String) {
//        dismissLoadingDialog()
        Toast.makeText(context, "회원가입 실패", Toast.LENGTH_SHORT)
    }
}