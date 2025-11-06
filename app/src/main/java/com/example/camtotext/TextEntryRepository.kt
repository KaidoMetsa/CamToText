package com.example.camtotext

import androidx.lifecycle.LiveData

class TextEntryRepository(private val textEntryDao: TextEntryDao) {
    val allEntries: LiveData<List<TextEntry>> = textEntryDao.getAllEntries()

    suspend fun insert(entry: TextEntry) {
        textEntryDao.insert(entry)
    }

    suspend fun deleteAll() {
        textEntryDao.deleteAll()
    }

    suspend fun deleteById(id: Long) {
        textEntryDao.deleteById(id)
    }

    suspend fun update(entry: TextEntry) {
        textEntryDao.update(entry)
    }
}