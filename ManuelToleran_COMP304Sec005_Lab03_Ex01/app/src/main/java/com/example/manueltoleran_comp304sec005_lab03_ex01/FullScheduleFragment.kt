package com.example.manueltoleran_comp304sec005_lab03_ex01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.manueltoleran_comp304sec005_lab03_ex01.databinding.FragmentFullScheduleBinding
import com.example.manueltoleran_comp304sec005_lab03_ex01.viewmodels.AirlineScheduleListViewModel
import com.example.manueltoleran_comp304sec005_lab03_ex01.viewmodels.AirlineScheduleViewModelFactory
import kotlinx.coroutines.launch

class FullScheduleFragment : Fragment() {
    private var _binding: FragmentFullScheduleBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel: AirlineScheduleListViewModel by activityViewModels {
        AirlineScheduleViewModelFactory(
            (activity?.application as AirlineScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullScheduleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val airlineAdapter = AirlineListAdapter {
            val action = FullScheduleFragmentDirections
                .actionFullScheduleFragmentToAirlineScheduleFragment(
                    airlineName = it.airlineName
                )
            view.findNavController().navigate(action)
        }
        recyclerView.adapter = airlineAdapter
        lifecycle.coroutineScope.launch {
            viewModel.fullSchedule().collect() {
                airlineAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}