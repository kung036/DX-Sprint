package com.example.tour.src

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.tour.R
import com.example.tour.config.BaseFragment
import com.example.tour.databinding.FragmentMainBinding
import com.example.tour.databinding.FragmentTestBinding

private lateinit var binding: FragmentTestBinding

class TestFragment : Fragment() {
//class TestFragment : BaseFragment<FragmentTestBinding>(
//    FragmentTestBinding::bind, R.layout.fragment_test) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        touch()
//        binding.buttonDateDialog.setOnClickListener {
//            //Toast.makeText(context, "test ...", Toast.LENGTH_SHORT)
//        }

    //        view.findViewById<Button>(R.id.button_date_dialog).setOnClickListener {
//            Toast.makeText(context, "test ...", Toast.LENGTH_SHORT)
//        }
    }

    // 에러발생코드
//    fun touch() {
//        binding.buttonDateDialog.setOnClickListener {
//            Toast.makeText(context, "test ...", Toast.LENGTH_SHORT)
//        }
//    }
}