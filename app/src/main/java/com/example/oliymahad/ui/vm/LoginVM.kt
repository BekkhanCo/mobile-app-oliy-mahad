package com.example.oliymahad.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oliymahad.api.repository.LoginRepository
import com.example.oliymahad.model.AuthModel
import com.example.oliymahad.model.UserLogin
import com.example.oliymahad.resource.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginVM(private val loginRepository: LoginRepository) : ViewModel() {

    val userLogin: MutableLiveData<Resource<AuthModel>> = MutableLiveData()

    fun loginUser(user: UserLogin) = viewModelScope.launch {
        userLogin.postValue(Resource.Loading())
        val response = loginRepository.userLogin(user)
        userLogin.postValue(handleUserLogin(response))
    }

    private fun handleUserLogin(userLogin: Response<AuthModel>): Resource<AuthModel> {
        if (userLogin.isSuccessful) {
            userLogin.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(userLogin.message())
    }

}