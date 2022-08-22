package com.example.tour.src.my

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.tour.R
import com.example.tour.config.ApplicationClass
import com.example.tour.config.ApplicationClass.Companion.NICKNAME_TOKEN
import com.example.tour.config.ApplicationClass.Companion.USER_EMAIL
import com.example.tour.config.ApplicationClass.Companion.USER_IDX
import com.example.tour.config.ApplicationClass.Companion.USER_PW
import com.example.tour.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.tour.config.ApplicationClass.Companion.editor
import com.example.tour.config.ApplicationClass.Companion.sSharedPreferences
import com.example.tour.config.BaseFragment
import com.example.tour.databinding.*
import com.example.tour.src.crew.MainCrewFragment
import com.example.tour.src.home.CardClass
import com.example.tour.src.home.MainActivity
import com.example.tour.src.home.MainAdapter
import com.example.tour.src.my.login.MyLoginActivity
import com.example.tour.src.my.login.MyLoginActivityInterface
import com.example.tour.src.my.login.MyLoginService
import com.example.tour.src.my.login.model.PostLoginReq
import com.example.tour.src.my.login.model.PostLoginRes
import com.example.tour.src.my.signup.MySignupActivity

private lateinit var binding: FragmentMyBinding

//class MyFragment : Fragment() {
class MyFragment : BaseFragment<FragmentMyBinding>
    (FragmentMyBinding::bind, R.layout.fragment_my),
    MyLoginActivityInterface {

    // static 변수
    companion object {
        var count: Int = 0
    }

//    private var nickname: String? = null
//    lateinit var mainActivity: MainActivity
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mainActivity = context as MainActivity
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // SharedPreferences 저장 후 사용하기
//        editor.putString("X-ACCESS-TOKEN", "test01")
//        editor.putInt("USER_IDX", 10)
//        editor.commit()
        Log.d("shin", "Start MyFragment >>")
        Log.d("shin", "${sSharedPreferences.getString(X_ACCESS_TOKEN, "EMPTY")}")
//        Log.d("shin", "${sSharedPreferences.getString(NICKNAME_TOKEN, "EMPTY")}")
//        Log.d("shin", "${sSharedPreferences.getInt(USER_IDX, 0)}")
//        Log.d("shin", "${sSharedPreferences.getString("USER_IDX", "0")}")

        // 자동 로그인 or 로그인한 경우
        var nickname =
            ApplicationClass.sSharedPreferences.getString(ApplicationClass.NICKNAME_TOKEN, "EMPTY")
        if (nickname != "EMPTY" && count == 0) {
//        if(nickname != "EMPTY" && count2 == 0) {
            var id = sSharedPreferences.getString(USER_EMAIL, "EMPTY")!!
            var pw = sSharedPreferences.getString(USER_PW, "EMPTY")!!
            val postRequest = PostLoginReq(id, pw)
            MyLoginService(this).tryPostLogin(postRequest)

            binding.myNickname.visibility = View.VISIBLE
            binding.myNickname.text = nickname
            binding.myButtonLogin.visibility = View.GONE
            binding.myButtonSignup.visibility = View.GONE

            count++;
            Log.d("shin", "자동로그인 : ${count}")
        }

        // 로그아웃 버튼 유무
        if (nickname != "EMPTY") {
            binding.logOut.visibility = View.VISIBLE
        } else {
            binding.logOut.visibility = View.GONE
        }

        // 회원가입
        binding.myButtonSignup.setOnClickListener {
            startActivity(Intent(context, MySignupActivity::class.java))
        }
        // 로그인
        binding.myButtonLogin.setOnClickListener {
            startActivity(Intent(context, MyLoginActivity::class.java))
        }
        // 로그아웃
        binding.logOut.setOnClickListener {
            Log.d("shin", sSharedPreferences.getString(NICKNAME_TOKEN, "EMPTY")!!)
            editor.putString(NICKNAME_TOKEN, "EMPTY")
            editor.commit()
            Log.d("shin", sSharedPreferences.getString(NICKNAME_TOKEN, "EMPTY")!!)
            binding.logOut.visibility = View.GONE
            binding.myNickname.visibility = View.GONE
            //binding.myNickname.text = nickname
            binding.myButtonLogin.visibility = View.VISIBLE
            binding.myButtonSignup.visibility = View.VISIBLE
        }

        // 모임창으로 이동
        binding.testbutton.setOnClickListener {
//            mainActivity.supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MainCrewFragment()).commit()
        }
    }

    // 다른 액티비티에서 돌아왔을 때, 화면 리프레시
    override fun onResume() {
        super.onResume()
        refresh()
    }

    // refresh
    fun refresh() {
        // 자동 로그인 or 로그인한 경우
        var nickname =
            ApplicationClass.sSharedPreferences.getString(ApplicationClass.NICKNAME_TOKEN, "EMPTY")
        if (nickname != "EMPTY") {
            binding.logOut.visibility = View.VISIBLE
            binding.myNickname.visibility = View.VISIBLE
            binding.myNickname.text = nickname
            binding.myButtonLogin.visibility = View.GONE
            binding.myButtonSignup.visibility = View.GONE

            count++;
            Log.d("shin", "${count}")
        }
    }

    override fun onPostLoginSuccess(response: PostLoginRes) {
//        dismissLoadingDialog()
        when (response.message) {
            "요청에 성공하였습니다." -> {
//                ApplicationClass.editor.putString(ApplicationClass.NICKNAME_TOKEN, response.result.userIdx.toString())
                ApplicationClass.editor.putString(NICKNAME_TOKEN, response.result.userNickName)
                ApplicationClass.editor.putString(
                    ApplicationClass.X_ACCESS_TOKEN,
                    response.result.jwt
                )
                ApplicationClass.editor.putInt(ApplicationClass.USER_IDX, response.result.userNo)
                ApplicationClass.editor.commit()

                Log.d("shin", "자동 로그인 >> ")
//                Log.d("shin", "${response.result.userIdx}")
                Log.d(
                    "shin",
                    ApplicationClass.sSharedPreferences.getString(
                        ApplicationClass.X_ACCESS_TOKEN,
                        ""
                    ).toString()
                )
//                Log.d("shin", "${ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_IDX, 0)}")
            }
            else -> {
//                Toast.makeText(context, "${response.message.toString()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPostLoginFailure(message: String) {
//        Toast.makeText(context, "회원가입 실패", Toast.LENGTH_SHORT).show()
    }
}
