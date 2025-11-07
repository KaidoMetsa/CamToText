package com.example.camtotext

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SharedTextViewModel(private val repository: TextEntryRepository) : ViewModel() {

    val allEntries: LiveData<List<TextEntry>> = repository.allEntries

    fun addText(text: String) = viewModelScope.launch {
        val title = "${(allEntries.value?.size ?: 0) + 1} pilt"
        repository.insert(TextEntry(title = title, text = text))
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun deleteEntry(id: Long) = viewModelScope.launch {
        repository.deleteById(id)
    }

    fun updateEntry(entry: TextEntry) = viewModelScope.launch {
        repository.update(entry)
    }
}
