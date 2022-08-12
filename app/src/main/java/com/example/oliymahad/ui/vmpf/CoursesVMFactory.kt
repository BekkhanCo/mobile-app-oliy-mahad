package com.example.oliymahad.ui.vmpf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oliymahad.api.repository.CourseRepository
import com.example.oliymahad.ui.vm.CoursesViewModel

class CoursesVMFactory(private val repository: CourseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CoursesViewModel(repository = repository) as T
    }
}