package com.appify.kotlintutorial.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appify.kotlintutorial.model.Task
import com.appify.kotlintutorial.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskViewModel: ViewModel() {
    var tasksLiveData = MutableLiveData<List<Task>>()
    var errorViewModel = MutableLiveData<Boolean>()

    fun getTasks() {
        Log.d("Viewmodel", "Trying to make call")
        Repository().call.enqueue(object: Callback<List<Task>> {
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                Log.d("Repository", "In the enqueue method")
                if (response.isSuccessful) {
                    Log.d("Retrofit", "Response is successful")
                    tasksLiveData.postValue(response.body())
                } else {
                    errorViewModel.value = true
                    Log.d("Retrofit", "Response is not successful. Error: ${response.message()}")
                    Log.d("Retrofit", "Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                Log.d("Retrofit", "Retrofit error ${t.message}")
                errorViewModel.value = true
            }

        })
    }

}