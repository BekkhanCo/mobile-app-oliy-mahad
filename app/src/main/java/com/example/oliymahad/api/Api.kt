package com.example.oliymahad.api

import com.example.oliymahad.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {
    @GET("course/get/list")
    suspend fun getCourses(): Response<CoursesModel<Data>>

    @Headers("Content-Type: application/json")
    @POST("user/auth/register")
    suspend fun register(
        @Body userRegisterRequest: UserRegister
    ): Response<AuthModel>

    @Headers("Content-Type: application/json")
    @POST("user/auth/login")
    suspend fun login(
        @Body userLoginRequest: UserLogin
    ): Response<AuthModel>
}