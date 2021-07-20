package com.appify.kotlintutorial.repository

import com.appify.kotlintutorial.retrofit.RetrofitService

class Repository {
    private val request = RetrofitService.client
    val call = request.getTasks()
}