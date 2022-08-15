package com.example.oliymahad.model

import java.io.Serializable

data class UserLogin(
    val password: String,
    val phoneNumber: String
) : Serializable