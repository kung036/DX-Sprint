package com.example.tour.src.crew.make

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tour.config.ApplicationClass.Companion.editor
import com.example.tour.databinding.FragmentBottomSheetDateBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private lateinit var binding: FragmentBottomSheetDateBinding

class DateBottomSheetFragment(context:Context) : BottomSheetDialogFragment(){
    private var year1:Int = 0
    private var month1:Int = 0
    private var day1:Int = 0
    private var customDialogListener: CustomDialogListener? = null

    //인터페이스 설정
    interface CustomDialogListener {
        fun onPositiveClicked(date: String)
        fun onNegativeClicked()
    }

    //호출할 리스너 초기화
    fun setDialogListener(customDialogListener: CustomDialogListener) {
        this.customDialogListener = customDialogListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBottomSheetDateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            year1 = year
            month1 = month
            day1 = day

        }

        binding.dateSelect.setOnClickListener {
            customDialogListener?.onPositiveClicked("${year1}년 ${month1+1}월 ${day1}일")
            dialog?.dismiss()
        }
    }
}