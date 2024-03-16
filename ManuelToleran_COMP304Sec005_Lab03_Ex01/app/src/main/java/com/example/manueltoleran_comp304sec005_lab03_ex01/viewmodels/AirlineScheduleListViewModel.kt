package com.example.manueltoleran_comp304sec005_lab03_ex01.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.manueltoleran_comp304sec005_lab03_ex01.database.schedule.AirlineSchedule
import com.example.manueltoleran_comp304sec005_lab03_ex01.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

class AirlineScheduleListViewModel(private val scheduleDao: ScheduleDao): ViewModel() {
    fun fullSchedule(): Flow<List<AirlineSchedule>> = scheduleDao.getAll()

    fun scheduleForStopName(name: String): Flow<List<AirlineSchedule>> = scheduleDao.getByAirlineName(name)
}

class AirlineScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirlineScheduleListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AirlineScheduleListViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}