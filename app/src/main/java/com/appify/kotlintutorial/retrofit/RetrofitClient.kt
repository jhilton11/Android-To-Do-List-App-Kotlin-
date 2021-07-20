package com.appify.kotlintutorial.retrofit

import com.appify.kotlintutorial.model.Task
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitClient {
    @GET(".")
    fun getTasks(): Call<List<Task>>
}