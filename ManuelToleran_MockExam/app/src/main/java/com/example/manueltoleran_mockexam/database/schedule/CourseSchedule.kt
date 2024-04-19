package com.example.manueltoleran_mockexam.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CourseSchedule (
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "course_name") val courseName: String,

    @NonNull @ColumnInfo(name = "course_code") val courseCode: String,
    @NonNull @ColumnInfo(name = "course_credits") val courseCredits: Int,
    @NonNull @ColumnInfo(name = "course_time") val courseTime: Int
)