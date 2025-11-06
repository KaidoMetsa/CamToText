package com.example.camtotext

import android.app.Application

class CamToTextApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { TextEntryRepository(database.textEntryDao()) }
}