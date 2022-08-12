package com.example.oliymahad.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oliymahad.api.repository.CourseRepository
import com.example.oliymahad.model.CoursesModel
import com.example.oliymahad.model.Data
import com.example.oliymahad.resource.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class CoursesViewModel(
    val repository: CourseRepository
) : ViewModel() {
    val allCourses: MutableLiveData<Resource<Data>> = MutableLiveData()



    fun getCourses() = viewModelScope.launch {
        allCourses.postValue(Resource.Loading())
        val coursesRep = repository.getAllCourses()
        allCourses.postValue(courseHandler(coursesRep))
    }

    private fun courseHandler(response: Response<CoursesModel<Data>>): Resource<Data> {

        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse.data)
            }
        }
        return Resource.Error(response.message())
    }


}