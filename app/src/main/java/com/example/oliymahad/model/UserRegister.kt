package com.example.oliymahad.model

import java.io.Serializable

data class UserRegister(
    val email: String,
    val password: String,
    val phoneNumber: String
):Serializable
