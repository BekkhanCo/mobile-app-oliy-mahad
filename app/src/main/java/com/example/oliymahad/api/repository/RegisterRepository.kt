package com.example.oliymahad.api.repository

import com.example.oliymahad.api.RetrofitInstance
import com.example.oliymahad.model.AuthModel
import com.example.oliymahad.model.UserRegister
import retrofit2.Response

class RegisterRepository {
    suspend fun registerUser(userRegister: UserRegister): Response<AuthModel> =
        RetrofitInstance.getApiServices().register(userRegister)
}