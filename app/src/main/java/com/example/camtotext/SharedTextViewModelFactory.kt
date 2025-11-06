package com.example.camtotext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SharedTextViewModelFactory(private val repository: TextEntryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedTextViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SharedTextViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}