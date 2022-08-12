package com.example.oliymahad.ui.vmpf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oliymahad.api.repository.RegisterRepository
import com.example.oliymahad.ui.vm.RegisterViewModel

class RegisterVMFactory(private val repository: RegisterRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel(registerRepository = repository) as T
    }
}