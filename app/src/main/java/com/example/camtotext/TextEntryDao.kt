package com.example.camtotext

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TextEntryDao {
    @Query("SELECT * FROM text_entries ORDER BY id DESC")
    fun getAllEntries(): LiveData<List<TextEntry>>

    @Insert
    suspend fun insert(entry: TextEntry)

    @Query("DELETE FROM text_entries")
    suspend fun deleteAll()

    @Query("DELETE FROM text_entries WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Update
    suspend fun update(entry: TextEntry)
}