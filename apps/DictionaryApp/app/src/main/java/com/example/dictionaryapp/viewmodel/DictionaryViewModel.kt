// Import necessary packages and classes
package com.example.dictionaryapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.DictionaryStorage
import com.example.dictionaryapp.database.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Define the DictionaryViewModel class, extending ViewModel
class DictionaryViewModel(private val repository: DictionaryStorage) : ViewModel() {

    // LiveData representing all words in the dictionary
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    // LiveData representing active words in the dictionary
    val activeWords: LiveData<List<Word>> = repository.activeWords.asLiveData()

    // LiveData representing inactive words in the dictionary
    val inactiveWords: LiveData<List<Word>> = repository.inactiveWords.asLiveData()

    // Method to insert a new word into the dictionary
    fun insertWord(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }

    // Method to activate a word in the dictionary
    fun activateWord(word: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.activate(word)
    }

    // Method to deactivate a word in the dictionary
    fun deactivateWord(word: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.deactivate(word)
    }

    // Method to check if a word exists in the dictionary and return the count
    fun checkWordExist(word: String): LiveData<Int> {
        Log.d("check exist", "${repository.checkWordCount(word).asLiveData().value}")
        return repository.checkWordCount(word).asLiveData()
    }
}

// Define the DictionaryViewModelFactory class implementing ViewModelProvider.Factory
class DictionaryViewModelFactory(private val repository: DictionaryStorage) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the requested ViewModel is DictionaryViewModel and create an instance
        if (modelClass.isAssignableFrom(DictionaryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DictionaryViewModel(repository) as T
        }
        // Throw an exception if an unknown ViewModel class is requested
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
