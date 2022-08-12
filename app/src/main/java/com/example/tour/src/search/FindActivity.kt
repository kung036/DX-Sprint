package com.example.tour.src.search

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.tour.databinding.ActivityFindBinding

class FindActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFindBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFindBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}