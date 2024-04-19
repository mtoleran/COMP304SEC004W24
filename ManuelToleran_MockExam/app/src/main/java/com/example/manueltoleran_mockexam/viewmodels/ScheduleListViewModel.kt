package com.example.manueltoleran_mockexam.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.manueltoleran_mockexam.database.schedule.CourseSchedule
import com.example.manueltoleran_mockexam.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

class ScheduleListViewModel(private val scheduleDao: ScheduleDao): ViewModel() {
    fun fullSchedule(): Flow<List<CourseSchedule>> = scheduleDao.getAll()

    fun scheduleForCourseName(name: String): Flow<List<CourseSchedule>> = scheduleDao.getByCourseName(name)
}

class CourseScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ScheduleListViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}