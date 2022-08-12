package com.example.oliymahad.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.oliymahad.databinding.ActivitySplashBinding


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Handler().postDelayed({
//            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 1000)

    }
}