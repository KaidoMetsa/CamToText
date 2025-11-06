package com.example.camtotext

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "text_entries")
data class TextEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    var text: String
)