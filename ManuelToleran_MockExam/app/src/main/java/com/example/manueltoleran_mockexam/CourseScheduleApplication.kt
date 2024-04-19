package com.example.manueltoleran_mockexam

import android.app.Application
import com.example.manueltoleran_mockexam.database.CourseDatabase

class CourseScheduleApplication : Application() {
    val database: CourseDatabase by lazy { CourseDatabase.getDatabase(this) }
}