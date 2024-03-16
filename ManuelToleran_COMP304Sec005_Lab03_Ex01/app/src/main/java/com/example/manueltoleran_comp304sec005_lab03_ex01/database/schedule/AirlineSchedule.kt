package com.example.manueltoleran_comp304sec005_lab03_ex01.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AirlineSchedule(
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "airline_name") val airlineName: String,
    @NonNull @ColumnInfo(name = "arrival_time") val arrivalTime: Int,
    @NonNull @ColumnInfo(name = "terminal") val terminal: String,
    @NonNull @ColumnInfo(name = "status") val status: String
)