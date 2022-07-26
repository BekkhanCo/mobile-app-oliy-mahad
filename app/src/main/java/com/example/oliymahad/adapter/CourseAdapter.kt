package com.example.oliymahad.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oliymahad.databinding.CoursesItemBinding
import com.example.oliymahad.model.CourseModel

class CourseAdapter(private val list: ArrayList<CourseModel>, val context: Context) :
    RecyclerView.Adapter<CourseAdapter.Holder>() {
    inner class Holder(private val binding: CoursesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(pos:Int){
                val currentItem=list[pos]
                binding.courseDescription.text=currentItem.description
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            CoursesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = list.size
}