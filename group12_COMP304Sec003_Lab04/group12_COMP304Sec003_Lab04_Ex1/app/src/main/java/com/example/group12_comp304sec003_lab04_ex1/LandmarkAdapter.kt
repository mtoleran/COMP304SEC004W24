package com.example.group12_comp304sec003_lab04_ex1
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.group12_comp304sec003_lab04_ex1.R

class LandmarkAdapter(
    private val context: Context,
    private val landmarkTypes: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<LandmarkAdapter.LandmarkViewHolder>() {

    inner class LandmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewLandmarkType: TextView = itemView.findViewById(R.id.textViewLandmarkType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_landmark, parent, false)
        return LandmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: LandmarkViewHolder, position: Int) {
        val landmarkType = landmarkTypes[position]
        holder.textViewLandmarkType.text = landmarkType

        holder.itemView.setOnClickListener {
            onItemClick(landmarkType)
        }
    }

    override fun getItemCount(): Int {
        return landmarkTypes.size
    }
}
