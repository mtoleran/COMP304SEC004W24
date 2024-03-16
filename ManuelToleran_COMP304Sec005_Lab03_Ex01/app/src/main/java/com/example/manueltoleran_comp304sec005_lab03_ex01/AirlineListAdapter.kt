package com.example.manueltoleran_comp304sec005_lab03_ex01

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.manueltoleran_comp304sec005_lab03_ex01.databinding.AirlineItemsBinding
import com.example.manueltoleran_comp304sec005_lab03_ex01.database.schedule.AirlineSchedule
import java.text.SimpleDateFormat
import java.util.Date

class AirlineListAdapter(
    private val onItemClicked: (AirlineSchedule) -> Unit
) : ListAdapter<AirlineSchedule, AirlineListAdapter.AirlineViewHolder>(DiffCallback){

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<AirlineSchedule>() {
            override fun areItemsTheSame(oldItem: AirlineSchedule, newItem: AirlineSchedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AirlineSchedule, newItem: AirlineSchedule): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirlineViewHolder {
        val viewHolder = AirlineViewHolder(
            AirlineItemsBinding.inflate(
                LayoutInflater.from( parent.context),
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

    override fun onBindViewHolder(holder: AirlineViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AirlineViewHolder(
        private var binding: AirlineItemsBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(schedule: AirlineSchedule) {
            binding.airlineNameTextView.text = schedule.airlineName
            binding.arrivalTimeTextView.text = SimpleDateFormat(
                "h:mm a").format(Date(schedule.arrivalTime.toLong() * 1000)
            )
            binding.terminalTextView.text = schedule.terminal

        }
    }

}