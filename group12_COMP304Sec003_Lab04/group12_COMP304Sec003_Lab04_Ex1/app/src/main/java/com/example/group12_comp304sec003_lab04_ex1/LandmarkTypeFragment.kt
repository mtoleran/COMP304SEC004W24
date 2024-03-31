package com.example.group12_comp304sec003_lab04_ex1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.group12_comp304sec003_lab04_ex1.R

class LandmarkTypeFragment : Fragment() {
    private val landmarkTypes = arrayOf("Old Building", "Towers", "Nature")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_landmark_type, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewLandmarkTypes)
        val adapter = LandmarkAdapter(requireContext(), landmarkTypes.toList()) { selectedLandmarkType ->
            val action =
                LandmarkTypeFragmentDirections.actionLandmarkTypeFragmentToLandmarkFragment(selectedLandmarkType)
            findNavController().navigate(action)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        return view
    }
}
