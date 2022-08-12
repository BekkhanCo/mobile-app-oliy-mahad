package com.example.oliymahad.model

data class CoursesModel<T>(
    val data: T,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)