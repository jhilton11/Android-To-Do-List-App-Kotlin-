package com.appify.kotlintutorial.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Task (
    @SerializedName("userId") var id: String,
    @SerializedName("title") var task: String,
    var createdAt: Date? = null,
    @SerializedName("completed") var isCompleted: Boolean) {
    var description: String? = null
    var completionDate: Date? = null
}