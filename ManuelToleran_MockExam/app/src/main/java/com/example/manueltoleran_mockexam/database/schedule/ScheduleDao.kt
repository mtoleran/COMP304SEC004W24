package com.example.manueltoleran_mockexam.database.schedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM courseschedule ORDER BY course_time ASC")
    fun getAll(): Flow<List<CourseSchedule>>

    @Query("SELECT * FROM courseschedule WHERE course_name = :courseName ORDER BY course_time ASC")
    fun getByCourseName(courseName: String): Flow<List<CourseSchedule>>
}