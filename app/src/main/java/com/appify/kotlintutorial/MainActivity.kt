package com.appify.kotlintutorial

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appify.kotlintutorial.adapter.TaskAdapter
import com.appify.kotlintutorial.databinding.ActivityMainBinding
import com.appify.kotlintutorial.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerview: RecyclerView
    private lateinit var progressBar: ProgressBar
    lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val queryTextInput = binding.queryTextField
        val queryInputLayout = binding.queryTextLayout
        progressBar = binding.progressBar
        recyclerview = binding.recyclerView
        recyclerview.layoutManager = LinearLayoutManager(this)

        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNewTaskActivity::class.java)
            startActivity(intent)
        }

        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(queryTextInput.windowToken, 0)
        inputMethodManager.hideSoftInputFromWindow(queryTextInput.windowToken, 0)

        loadTasks()
    }

    private fun loadTasks() {
        Log.d("retrofit", "Loading tasks")
        progressBar.visibility = View.VISIBLE

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskViewModel.tasksLiveData.observe(this, { tasks ->
            recyclerview.adapter = TaskAdapter(tasks)
            progressBar.visibility = View.GONE
            Log.d("Observer", "Finished loading tasks")
        })

        taskViewModel.errorViewModel.observe(this, {
            if (it) {
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Query unsuccessful", Toast.LENGTH_LONG).show()
            }
        })

        taskViewModel.getTasks()

    }

}