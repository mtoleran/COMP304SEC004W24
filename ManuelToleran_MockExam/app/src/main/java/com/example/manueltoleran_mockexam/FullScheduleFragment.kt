package com.example.manueltoleran_mockexam

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
import com.example.manueltoleran_mockexam.viewmodels.CourseScheduleViewModelFactory
import com.example.manueltoleran_mockexam.viewmodels.ScheduleListViewModel
import com.example.manueltoleran_mockexam.databinding.FragmentFullScheduleBinding
import kotlinx.coroutines.launch


class FullScheduleFragment : Fragment() {
    private var _binding: FragmentFullScheduleBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel: ScheduleListViewModel by activityViewModels {
        CourseScheduleViewModelFactory(
            (activity?.application as CourseScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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
        val courseListAdapter = CourseListAdapter(
            showTime = false,{
                val action = FullScheduleFragmentDirections
                    .actionFullScheduleFragmentToDetailedScheduleFragment(it.courseName)

                view.findNavController().navigate(action)
            })
        recyclerView.adapter = courseListAdapter
        lifecycle.coroutineScope.launch {
            viewModel.fullSchedule().collect() {
                courseListAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}