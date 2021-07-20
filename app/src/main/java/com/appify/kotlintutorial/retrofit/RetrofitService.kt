package com.appify.kotlintutorial.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/todos/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().build())
                .build()
    }

    val client: RetrofitClient by lazy {
        retrofit.create(RetrofitClient::class.java)
    }
}