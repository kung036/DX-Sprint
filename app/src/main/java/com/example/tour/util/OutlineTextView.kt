package com.example.tour.util

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

@SuppressLint("NotConstructor")
class OutlineTextView(context: Context) : AppCompatTextView(context) {

    private var stroke = false

    private var strokeWidth = 0.0f

    private var strokeColor = 0


    fun OutlineTextView(context: Context?) {
        //super(context)
    }


    fun OutlineTextView(context: Context, attrs: AttributeSet) {
        //super(context, attrs)
        //initView(context, attrs)
    }


    fun OutlineTextView(context: Context, attrs: AttributeSet, defStyleAttr: Int) {
        //super(context, attrs, defStyleAttr)
        //initView(context, attrs)
    }

    /*@SuppressLint("CustomViewStyleable")
    private fun initView(context: Context, attrs: AttributeSet) {
        val type = context.obtainStyledAttributes(attrs, R.styleable.OutlineTextView)
        stroke = type.getBoolean(R.styleable.OutlineTextView_textStroke, false) // 외곽선 유무
        strokeWidth = type.getFloat(R.styleable.OutlineTextView_textStrokeWidth, 0.0f) // 외곽선 두께
        strokeColor = type.getColor(R.styleable.OutlineTextView_textStrokeColor, -0x1) // 외곽선
    }*/


    override fun onDraw(canvas: Canvas?) {
        if (stroke) {
            val states: ColorStateList = textColors
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = strokeWidth
            setTextColor(strokeColor)
            super.onDraw(canvas)
            paint.style = Paint.Style.FILL
            setTextColor(states)
        }
        super.onDraw(canvas)
    }

}