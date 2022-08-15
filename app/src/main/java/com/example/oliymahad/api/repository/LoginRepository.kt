package com.example.oliymahad.api.repository

import com.example.oliymahad.api.RetrofitInstance
import com.example.oliymahad.model.AuthModel
import com.example.oliymahad.model.UserLogin
import retrofit2.Response

class LoginRepository {
    suspend fun userLogin(userLogin: UserLogin): Response<AuthModel> =
        RetrofitInstance.getApiServices().login(userLogin)
}