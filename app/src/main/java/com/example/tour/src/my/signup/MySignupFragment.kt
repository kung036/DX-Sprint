package com.example.tour.src.my.signup

import androidx.fragment.app.Fragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tour.R
import com.example.tour.config.ApplicationClass
import com.example.tour.config.ApplicationClass.Companion.USER_IDX
import com.example.tour.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.tour.config.ApplicationClass.Companion.editor
import com.example.tour.config.BaseFragment
import com.example.tour.databinding.FragmentMySignupBinding
import com.example.tour.src.home.MainActivity
import com.example.tour.src.my.MyFragment
import com.example.tour.src.my.login.MyLoginFragment
import com.example.tour.src.my.signup.model.PostSignUpReq
import com.example.tour.src.my.signup.model.PostSignUpRes

//private lateinit var binding: FragmentMySignupBinding

//class MySignupFragment : Fragment() {
class MySignupFragment : BaseFragment<FragmentMySignupBinding>
    (FragmentMySignupBinding::bind, R.layout.fragment_my_signup),
    MySignupFragmentInterface {

    // fragment에서 runOnithread 사용하기
    lateinit var mainActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
//        service = context as MySignupService
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupButton.setOnClickListener {
            // 아이디 확인
            var id = binding.signupId.text.toString()
            var id2 = id.split("@")[1].split(".")
//            Log.d("shin", "${id}")
            if (id2.size != 2 || id2[0].length == 0 || id2[1].length == 0)
                Toast.makeText(context, "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show()

            // 비밀번호 확인
            var pw = binding.signupPw.text.toString()
            if (pw.length < 8 || pw.length > 14)
                Toast.makeText(context, "비밀번호는 8 ~ 12자리입니다.", Toast.LENGTH_SHORT).show()
            if (pw != binding.signupPw2.text.toString())
                Toast.makeText(context, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show()

            // 연락처 확인
            var phone = binding.signupPhone.text.toString()
            var phone2 = phone.split("-")
//            Log.d("shin", "${phone}")
            if (phone.length != 13 || phone2[0].length != 3 || phone2[1].length != 4 || phone2[2].length != 4)
                Toast.makeText(context, "연락처 형식은 010-0000-0000 입니다.", Toast.LENGTH_SHORT).show()

            // 닉네임 확인
            var nickname = binding.signupNickname.text.toString()
            if (nickname.length < 2 || nickname.length > 6)
                Toast.makeText(context, "닉네임은 2 ~ 6글자 사이입니다.", Toast.LENGTH_SHORT).show()

            // 서버 전달
            val postRequest = PostSignUpReq(id, pw, phone, nickname)
//            showLoadingDialog(context)
            MySignupService(this).tryPostSignUp(postRequest)
        }
    }

    override fun onPostSignUpSuccess(response: PostSignUpRes) {
//        dismissLoadingDialog()
        when (response.message) {
            "요청에 성공하였습니다." -> {
                Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                editor.putString(X_ACCESS_TOKEN, response.result.jwt)
                editor.putInt(USER_IDX, response.result.userIdx)
                editor.commit()
                Log.d(
                    "shin",
                    "${
                        ApplicationClass.sSharedPreferences.getString(
                            X_ACCESS_TOKEN,
                            "X_ACCESS_TOKEN_EMPTY"
                        )
                    }"
                )
                Log.d(
                    "shin",
                    "${ApplicationClass.sSharedPreferences.getString(USER_IDX, "USER_IDX_EMPTY")}"
                )

                mainActivity.supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MyLoginFragment()).commit()
            }
            else -> {
                Toast.makeText(context, response.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPostSignUpFailure(message: String) {
//        dismissLoadingDialog()
        Toast.makeText(context, "회원가입 실패", Toast.LENGTH_SHORT)
    }
}