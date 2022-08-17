package com.example.tour.src.crew.make

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tour.databinding.FragmentBottomSheetTimeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private lateinit var binding: FragmentBottomSheetTimeBinding

class TimeBottomSheetFragment(context: Context) : BottomSheetDialogFragment(){
    private var hour1:Int = 0
    private var min1:Int = 0
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
        binding = FragmentBottomSheetTimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.timeSet.setOnTimeChangedListener { timePicker, hour, min ->
            hour1 = hour
            min1 = min
        }

        binding.dateSelect.setOnClickListener {
            customDialogListener?.onPositiveClicked("${hour1}:${min1}")
            dialog?.dismiss()
        }
    }
}