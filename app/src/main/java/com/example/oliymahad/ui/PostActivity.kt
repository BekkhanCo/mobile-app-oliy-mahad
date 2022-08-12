package com.example.oliymahad.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.oliymahad.R
import com.example.oliymahad.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPostBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val title = intent.getStringExtra("courseTitle")
        val price = intent.getStringExtra("coursePrice")
        val duration = intent.getStringExtra("courseDuration")
        binding.postTitle.text = title
        binding.postDate.text = duration
        binding.postPrice.text = price


    }
}