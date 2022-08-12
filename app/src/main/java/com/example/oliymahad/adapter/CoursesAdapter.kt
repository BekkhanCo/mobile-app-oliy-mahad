package com.example.oliymahad.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.oliymahad.databinding.CoursesItemBinding
import com.example.oliymahad.model.Content
import com.example.oliymahad.ui.PostActivity

class CoursesAdapter : RecyclerView.Adapter<CoursesAdapter.Holder>() {

    inner class Holder(private val binding: CoursesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(pos: Int) {
            val price ="${(differ.currentList[pos].price).toInt()} 000 UZS"
            val title = differ.currentList[pos].name
            val description = differ.currentList[pos].description
            val duration = "${differ.currentList[pos].duration.toInt()} month"
            binding.courseDescription.text = description
            binding.courseTitle.text = title
            binding.courseDuration.text = duration
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, PostActivity::class.java)
                intent.putExtra("courseTitle", title)
                intent.putExtra("coursePrice", price)
                intent.putExtra("courseDuration", duration)
                binding.root.context.startActivity(intent)
            }
        }

    }

    private val diffUtil = object : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)


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

    override fun getItemCount(): Int = differ.currentList.size
}