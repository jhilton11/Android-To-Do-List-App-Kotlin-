package com.appify.kotlintutorial.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.appify.kotlintutorial.AddNewTaskActivity
import com.appify.kotlintutorial.R
import com.appify.kotlintutorial.databinding.TaskRowLayoutBinding
import com.appify.kotlintutorial.model.Task

class TaskAdapter(private val tasksArray: List<Task>):
    RecyclerView.Adapter<TaskAdapter.Holder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.task_row_layout, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val task = tasksArray[position]

        holder.taskTv.text = task.task
        holder.isCompletedCb.isChecked = task.isCompleted

        holder.itemView.setOnClickListener {
            val intent = Intent(context, AddNewTaskActivity::class.java)
            context.startActivity(intent)
        }

        holder.isCompletedCb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(context, "Item on position ${position + 1} selected", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Item on position ${position + 1} deselected", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun getItemCount(): Int {
        return tasksArray.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = TaskRowLayoutBinding.bind(itemView)
        val dateTv = binding.dateTv
        val taskTv = binding.taskTv
        val isCompletedCb = binding.isCompletedCb
    }

}