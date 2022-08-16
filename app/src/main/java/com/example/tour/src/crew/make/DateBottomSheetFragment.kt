package com.example.tour.src.crew.make

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tour.config.ApplicationClass.Companion.editor
import com.example.tour.databinding.FragmentBottomSheetDateBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private lateinit var binding: FragmentBottomSheetDateBinding

class DateBottomSheetFragment : BottomSheetDialogFragment(){
    private var year1:Int = 0
    private var month1:Int = 0
    private var day1:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            editor.putString("selectDay","${year1}년 ${month1+1}월 ${day1}일")
            editor.commit()
            dialog?.dismiss()
        }
    }
}