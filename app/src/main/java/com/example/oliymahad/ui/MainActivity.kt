package com.example.oliymahad.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.oliymahad.R
import com.example.oliymahad.api.repository.CourseRepository
import com.example.oliymahad.api.repository.RegisterRepository
import com.example.oliymahad.databinding.ActivityMainBinding
import com.example.oliymahad.ui.fragment.CourseFragment
import com.example.oliymahad.ui.fragment.EventFragment
import com.example.oliymahad.ui.fragment.MyCourseFragment
import com.example.oliymahad.ui.fragment.NotificationFragment
import com.example.oliymahad.ui.vm.CoursesViewModel
import com.example.oliymahad.ui.vm.RegisterViewModel
import com.example.oliymahad.ui.vmpf.CoursesVMFactory
import com.example.oliymahad.ui.vmpf.RegisterVMFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModelCourse: CoursesViewModel
    lateinit var viewModelRegister: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val courseRepository = CourseRepository()
        val registerRepository = RegisterRepository()
        val vmFactoryR = RegisterVMFactory(registerRepository)
        val vmFactoryC = CoursesVMFactory(courseRepository)
        viewModelCourse = ViewModelProvider(this, vmFactoryC).get(CoursesViewModel::class.java)
        viewModelRegister = ViewModelProvider(this, vmFactoryR).get(RegisterViewModel::class.java)
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