package com.example.oliymahad.api

import okhttp3.Interceptor
import okhttp3.Response

class InterceptorAuth(private val accessToken: String) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "$accessToken").build()
        return chain.proceed(request)
    }

}
