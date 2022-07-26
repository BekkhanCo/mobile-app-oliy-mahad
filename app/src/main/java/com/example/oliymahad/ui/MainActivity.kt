package com.example.oliymahad.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.oliymahad.R
import com.example.oliymahad.databinding.ActivityMainBinding
import com.example.oliymahad.ui.fragment.CourseFragment
import com.example.oliymahad.ui.fragment.EventFragment
import com.example.oliymahad.ui.fragment.MyCourseFragment
import com.example.oliymahad.ui.fragment.NotificationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomNavBar.setOnNavigationItemSelectedListener(navListener)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, EventFragment())
            .commit()

    }

    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = EventFragment()
            when (item.itemId) {
                R.id.btm_events -> selectedFragment = EventFragment()
                R.id.btm_courses -> selectedFragment = CourseFragment()
                R.id.btm_my_courses -> selectedFragment = MyCourseFragment()
                R.id.btm_notification -> selectedFragment = NotificationFragment()
            }
            if (selectedFragment != null) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, selectedFragment)
                    .commit()
            }
            true
        }

}