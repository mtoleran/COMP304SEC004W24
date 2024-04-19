package com.example.manueltoleran_mockexam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.manueltoleran_mockexam.databinding.FragmentDetailedScheduleBinding
import com.example.manueltoleran_mockexam.viewmodels.CourseScheduleViewModelFactory
import com.example.manueltoleran_mockexam.viewmodels.ScheduleListViewModel
import kotlinx.coroutines.launch

class DetailedScheduleFragment : Fragment() {
    companion object {
        var COURSE_NAME = "courseName"
    }

    private var _binding: FragmentDetailedScheduleBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var courseName: String

    private val viewModel: ScheduleListViewModel by activityViewModels {
        CourseScheduleViewModelFactory(
            (activity?.application as CourseScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            courseName = it.getString(COURSE_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedScheduleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val courseListAdapter = CourseListAdapter(showTime = true,{})
        recyclerView.adapter = courseListAdapter
        lifecycle.coroutineScope.launch {
            viewModel.scheduleForCourseName(courseName).collect() {
                courseListAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}