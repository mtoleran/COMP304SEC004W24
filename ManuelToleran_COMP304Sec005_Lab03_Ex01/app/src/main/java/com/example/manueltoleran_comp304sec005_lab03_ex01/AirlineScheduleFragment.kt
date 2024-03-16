package com.example.manueltoleran_comp304sec005_lab03_ex01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.manueltoleran_comp304sec005_lab03_ex01.databinding.FragmentAirlineScheduleBinding
import com.example.manueltoleran_comp304sec005_lab03_ex01.viewmodels.AirlineScheduleListViewModel
import com.example.manueltoleran_comp304sec005_lab03_ex01.viewmodels.AirlineScheduleViewModelFactory
import kotlinx.coroutines.launch

class AirlineScheduleFragment : Fragment() {
    companion object {
        var AIRLINE_NAME = "airlineName"
    }

    private var _binding: FragmentAirlineScheduleBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var airlineName: String

    private val viewModel: AirlineScheduleListViewModel by activityViewModels {
        AirlineScheduleViewModelFactory(
            (activity?.application as AirlineScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            airlineName = it.getString(AIRLINE_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAirlineScheduleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val airlineAdapter = AirlineListAdapter({})
        // by passing in the airline name, filtered results are returned,
        // and tapping rows won't trigger navigation
        recyclerView.adapter = airlineAdapter
        lifecycle.coroutineScope.launch {
            viewModel.scheduleForStopName(airlineName).collect() {
                airlineAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}