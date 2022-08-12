package com.example.oliymahad.api.repository

import com.example.oliymahad.api.RetrofitInstance
import com.example.oliymahad.model.CoursesModel
import com.example.oliymahad.model.Data
import retrofit2.Response

class CourseRepository {
    suspend fun getAllCourses(): Response<CoursesModel<Data>> =
        RetrofitInstance.getApiServices1().getCourses()
}