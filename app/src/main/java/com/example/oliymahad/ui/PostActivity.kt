package com.example.oliymahad.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.oliymahad.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPostBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val isLoggedIn = false
        val title = intent.getStringExtra("courseTitle")
        val price = intent.getStringExtra("coursePrice")
        val duration = intent.getStringExtra("courseDuration")

        binding.postTitle.text = title
        binding.postDate.text = duration
        binding.postPrice.text = price

        binding.registerCourse.setOnClickListener {
            if (isLoggedIn) {
                startActivity(Intent(this, SignIn::class.java))
                finish()
            } else {
                val dialog =
                    AlertDialog.Builder(this).setTitle("Warning...")
                        .setMessage("Before continue, please create an account")
                        .setPositiveButton("Ok") { _, _ ->
                            startActivity(Intent(this, SignIn::class.java))
                            finish()
                        }
                        .create()
                dialog.show()
            }
        }

    }
}