package com.example.manueltoleran_mockexam

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.manueltoleran_mockexam.database.schedule.CourseSchedule
import com.example.manueltoleran_mockexam.databinding.CourseItemBinding
import java.text.SimpleDateFormat
import java.util.Date

class CourseListAdapter(
    private val showTime: Boolean = false,
    private val onItemClicked: (CourseSchedule) -> Unit
) : ListAdapter<CourseSchedule, CourseListAdapter.CourseViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<CourseSchedule>() {
            override fun areItemsTheSame(oldItem: CourseSchedule, newItem: CourseSchedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CourseSchedule, newItem: CourseSchedule): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val viewHolder = CourseViewHolder(
            CourseItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position), showTime)
    }

    class CourseViewHolder(
        private var binding: CourseItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(schedule: CourseSchedule, showTime: Boolean) {
            binding.courseNameTextView.text = schedule.courseName
            binding.courseCodeTextView.text = schedule.courseCode
            binding.courseCreditsTextView.text = schedule.courseCredits.toString()

            if (showTime) {
                binding.courseTimeView.visibility = View.VISIBLE
                binding.courseTimeView.text = SimpleDateFormat("h:mm a").format(Date(schedule.courseTime.toLong() * 1000))
            } else {
                binding.courseTimeView.visibility = View.GONE
            }
        }
    }
}