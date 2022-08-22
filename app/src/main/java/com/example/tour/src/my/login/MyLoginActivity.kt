package com.example.tour.src.my.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.tour.R
import com.example.tour.config.ApplicationClass
import com.example.tour.config.ApplicationClass.Companion.NICKNAME_TOKEN
import com.example.tour.config.BaseActivity
import com.example.tour.databinding.ActivityMyLoginBinding
import com.example.tour.src.home.MainActivity
import com.example.tour.src.my.MyFragment
import com.example.tour.src.my.login.model.PostLoginReq
import com.example.tour.src.my.login.model.PostLoginRes
import com.example.tour.src.my.signup.MySignupActivity

//private lateinit var binding: FragmentMySignupBinding

class MyLoginActivity : BaseActivity<ActivityMyLoginBinding>
    (ActivityMyLoginBinding::inflate), MyLoginActivityInterface {
    var id = ""
    var pw = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 회원가입 화면
        binding.loginButtonSignup.setOnClickListener {
            startActivity(Intent(this, MySignupActivity::class.java))
        }

        binding.loginButton.setOnClickListener {
            id = binding.loginId.text.toString()
            pw = binding.loginPw.text.toString()

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
//                showCustomToast("로그인 성공")
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

//                ApplicationClass.editor.putString(ApplicationClass.NICKNAME_TOKEN, response.result.userIdx.toString())
                ApplicationClass.editor.putString(NICKNAME_TOKEN, response.result.userNickName)
                ApplicationClass.editor.putString(ApplicationClass.X_ACCESS_TOKEN, response.result.jwt)
                ApplicationClass.editor.putInt(ApplicationClass.USER_IDX, response.result.userNo)
                ApplicationClass.editor.putString(ApplicationClass.USER_EMAIL, id)
                ApplicationClass.editor.putString(ApplicationClass.USER_PW, pw)
                ApplicationClass.editor.commit()

//                Log.d("shin", "MyLogingActiding >> ")
//                Log.d("shin", "${response.result.userIdx}")
//                Log.d("shin", ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN,"").toString())
//                Log.d("shin", "${ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_IDX, 0)}")
//                var nickname = ApplicationClass.sSharedPreferences.getString(NICKNAME_TOKEN, "EMPTY")
//                Log.d("shin", "${nickname}")
//                ApplicationClass.editor.commit()

                // 로그인 성공 시 화면 전환
//                startActivity(Intent(this, MainActivity::class.java))
//                var fragment: MyFragment = supportFragmentManager.(MyFragment())
//                fragment.refresh()

//                supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MyFragment()).commit()
                finish()
            }
            else -> {
                Toast.makeText(this, "${response.message.toString()}", Toast.LENGTH_SHORT).show()
//                showCustomToast(response.message.toString())
            }
        }
    }

    override fun onPostLoginFailure(message: String) {
//        dismissLoadingDialog()
        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
    }
}