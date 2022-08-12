package com.example.oliymahad.ui.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oliymahad.api.repository.RegisterRepository
import com.example.oliymahad.model.AuthModel
import com.example.oliymahad.model.UserRegister
import com.example.oliymahad.resource.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class RegisterViewModel(private val registerRepository: RegisterRepository) : ViewModel() {

    val user: MutableLiveData<Resource<AuthModel>> = MutableLiveData()


    fun registerUser(userRegister: UserRegister) = viewModelScope.launch {
        user.postValue(Resource.Loading())
        val response = registerRepository.registerUser(userRegister)
        Log.d("salom", response.body().toString())
        user.postValue(handleRegisterResponse(response))
    }

    private fun handleRegisterResponse(user: Response<AuthModel>): Resource<AuthModel> {
        if (user.isSuccessful) {
            user.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(user.message())
    }
}