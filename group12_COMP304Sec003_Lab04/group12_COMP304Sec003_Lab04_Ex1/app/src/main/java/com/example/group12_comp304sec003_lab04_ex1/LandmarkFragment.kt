package com.example.group12_comp304sec003_lab04_ex1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.group12_comp304sec003_lab04_ex1.R
import com.google.android.gms.maps.model.LatLng
import androidx.navigation.fragment.findNavController

class LandmarkFragment : Fragment() {
    private lateinit var selectedLandmarkType: String
    private val allLandmarks = arrayOf(
        "Colosseum", "Tower of Pisa", "Taj Mahal",
        "CN Tower", "Big Ben", "Eiffel Tower",
        "Banaue Rice Terraces", "Mount Everest", "Grand Canyon"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_landmark, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewLandmark)


        val args: LandmarkFragmentArgs by navArgs()
        selectedLandmarkType = args.selectedLandmarkType


        val filteredLandmarks = when (selectedLandmarkType) {
            "Old Building" -> allLandmarks.filter { it.startsWith("Colosseum") || it.startsWith("Tower of Pisa") || it.startsWith("Taj Mahal") }
            "Towers" -> allLandmarks.filter { it.startsWith("CN Tower") || it.startsWith("Big Ben") || it.startsWith("Eiffel Tower") }
            "Nature" -> allLandmarks.filter { it.startsWith("Banaue Rice Terraces") || it.startsWith("Mount Everest") || it.startsWith("Grand Canyon") }
            else -> emptyList()
        }

        val adapter = LandmarkAdapter(requireContext(), filteredLandmarks) { selectedItem ->
            Log.d("LandmarkFragment", "Selected item: $selectedItem")
            val locationLatLng = when (selectedItem) {
                "Colosseum" -> LatLng(41.89, 12.49)
                "Tower of Pisa" -> LatLng(43.72, 10.40)
                "Taj Mahal" -> LatLng(27.18, 78.04)
                "CN Tower" -> LatLng(43.64, -79.39)
                "Big Ben" -> LatLng(51.50, 0.12)
                "Eiffel Tower" -> LatLng(48.86, 2.29)
                "Banaue Rice Terraces" -> LatLng(16.93, 121.14)
                "Mount Everest" -> LatLng(27.99, 86.63)
                "Grand Canyon" -> LatLng(36.27, 112.35)
                else -> {
                    Log.e("LandmarkFragment", "Unknown landmark: $selectedItem")
                    LatLng(0.0, 0.0)
                }
            }


            val action = LandmarkFragmentDirections.actionLandmarkFragmentToMapFragment(locationLatLng)
            findNavController().navigate(action)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }
}
