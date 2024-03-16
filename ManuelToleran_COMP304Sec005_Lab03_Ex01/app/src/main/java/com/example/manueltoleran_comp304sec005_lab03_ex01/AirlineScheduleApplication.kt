package com.example.manueltoleran_comp304sec005_lab03_ex01

import android.app.Application
import com.example.manueltoleran_comp304sec005_lab03_ex01.database.AirlineDatabase

class AirlineScheduleApplication: Application() {
    val database: AirlineDatabase by lazy { AirlineDatabase.getDatabase(this) }
}