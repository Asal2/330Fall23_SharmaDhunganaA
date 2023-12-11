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


class DictionaryViewModel(private val repository: DictionaryStorage) : ViewModel() {

    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    val activeWords: LiveData<List<Word>> = repository.activeWords.asLiveData()

    val inactiveWords: LiveData<List<Word>> = repository.inactiveWords.asLiveData()

    fun insertWord(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }

    fun activateWord(word: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.activate(word)
    }

    fun deactivateWord(word: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.deactivate(word)
    }

    fun checkWordExist(word: String) :LiveData<Int> {
        Log.d("check exist","${repository.checkWordCount(word).asLiveData().value}")
        return repository.checkWordCount(word).asLiveData()
    }
}


class DictionaryViewModelFactory(private val repository: DictionaryStorage) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DictionaryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DictionaryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
