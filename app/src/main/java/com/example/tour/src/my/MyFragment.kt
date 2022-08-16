package com.example.tour.src.my

import android.app.Application
import android.app.SharedElementCallback
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings.Global.getString
import android.provider.Settings.System.getString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.fragment.app.Fragment
import com.example.tour.R
import com.example.tour.config.ApplicationClass
import com.example.tour.config.ApplicationClass.Companion.editor
import com.example.tour.config.ApplicationClass.Companion.sSharedPreferences
import com.example.tour.src.home.MainActivity
import com.example.tour.databinding.*
import com.example.tour.src.home.CardClass
import com.example.tour.src.home.MainAdapter
import com.example.tour.src.home.MainFragment
import com.example.tour.src.my.login.MyLoginFragment
import com.example.tour.src.my.signup.MySignupFragment

private lateinit var binding: FragmentMyBinding

class MyFragment : Fragment() {
    //class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::bind,
//    R.layout.fragment_main){
    private val dataSet = arrayListOf<CardClass>()
    private lateinit var rvAdapter: MainAdapter
    private var nickname: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
            nickname = it.getString("nickname")
        }

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMyBinding.inflate(inflater, container, false)

        return binding.root
    }

    // fragment에서 runOnithread 사용하기
    lateinit var mainActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // SharedPreferences 저장 후 사용하기
//        editor.putString("X-ACCESS-TOKEN", "test01")
//        editor.putString("USER_IDX", "12")
//        editor.commit()
        Log.d("shin", "${sSharedPreferences.getString("X-ACCESS-TOKEN", "EMPTY")}")
        Log.d("shin", "${sSharedPreferences.getInt("USER_IDX", 0)}")

        // 자동 로그인 or 로그인한 경우

        var nickname = ApplicationClass.sSharedPreferences.getString(ApplicationClass.NICKNAME_TOKEN, "EMPTY")
        if(nickname != "EMPTY") {
            binding.myNickname.visibility = View.VISIBLE
            binding.myNickname.text = nickname
            binding.myButtonLogin.visibility = View.GONE
            binding.myButtonSignup.visibility = View.GONE
        }

        // 회원가입
        binding.myButtonSignup.setOnClickListener {
            mainActivity.supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MySignupFragment()).commit()
        }
        // 로그인
        binding.myButtonLogin.setOnClickListener {
            mainActivity.supportFragmentManager.beginTransaction().replace(R.id.framelaout_container, MyLoginFragment()).commit()
        }
        binding.logOut.setOnClickListener {
            editor.putString(ApplicationClass.NICKNAME_TOKEN, "EMPTY")
            editor.commit()
            binding.myNickname.visibility = View.GONE
            //binding.myNickname.text = nickname
            binding.myButtonLogin.visibility = View.VISIBLE
            binding.myButtonSignup.visibility = View.VISIBLE
        }
    }
}