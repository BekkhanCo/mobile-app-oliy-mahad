package com.example.oliymahad.model

data class AuthModel(
    val accessToken: String,
    val message: String,
    val refreshToken: String,
    val statusCode: Int
)