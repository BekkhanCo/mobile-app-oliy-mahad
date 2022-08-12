package com.example.oliymahad.api

import com.example.oliymahad.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    var retrofit: Retrofit? = null
    var api: Api? = null
    fun getApiServices(): Api {
        retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        api = retrofit?.create(Api::class.java)
        return api!!
    }

    fun getApiServices1(): Api {
        val client = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(
                InterceptorAuth(
                    "Bearer",
                    "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIrOTk4OTE2NjQ1MjYyIiwicm9sZSI6W3siaWQiOjEsInJvbGVOYW1lIjoiUk9MRV9VU0VSIiwiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2NjAwMzExMzcsImV4cCI6MTY2MDAzNzEzN30.moTLEFMknOjU7CkdexiwwQJmjIcY1bddbX9b11KaX8MWoFzCRXSuqLBeKyv3Inz6HXlmhZUo10lI6GNpXAursw"
                )
            )
            .build()
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        api = retrofit?.create(Api::class.java)
        return api!!
    }


}