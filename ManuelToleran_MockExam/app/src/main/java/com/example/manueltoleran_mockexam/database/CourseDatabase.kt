package com.example.manueltoleran_mockexam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import com.example.manueltoleran_mockexam.database.schedule.CourseSchedule
import com.example.manueltoleran_mockexam.database.schedule.ScheduleDao

@Database(entities = arrayOf(CourseSchedule::class), version = 1)
abstract class CourseDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: CourseDatabase? = null

        fun getDatabase(context: Context): CourseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CourseDatabase::class.java,
                    "course_database")
                    .createFromAsset("database/course_database.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}