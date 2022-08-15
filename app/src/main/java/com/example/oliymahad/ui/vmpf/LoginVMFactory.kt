package com.example.oliymahad.ui.vmpf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oliymahad.api.repository.LoginRepository
import com.example.oliymahad.ui.vm.LoginVM

class LoginVMFactory(private val repository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginVM(repository) as T
    }
}