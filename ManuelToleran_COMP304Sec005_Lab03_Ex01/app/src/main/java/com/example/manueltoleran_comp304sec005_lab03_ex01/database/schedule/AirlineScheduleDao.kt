package com.example.manueltoleran_comp304sec005_lab03_ex01.database.schedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM AirlineSchedule ORDER BY arrival_time ASC")
    fun getAll(): Flow<List<AirlineSchedule>>

    @Query("SELECT * FROM AirlineSchedule WHERE airline_name = :airlineName ORDER BY arrival_time ASC")
    fun getByAirlineName(airlineName: String): Flow<List<AirlineSchedule>>

}