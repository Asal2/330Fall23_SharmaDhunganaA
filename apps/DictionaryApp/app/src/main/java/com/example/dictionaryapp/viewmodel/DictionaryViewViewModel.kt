// Import necessary packages and classes
package com.example.dictionaryapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.database.Word
import com.example.dictionaryapp.network.DictionaryApi
import com.example.dictionaryapp.network.parseJsonStringToListOfWords
import com.example.dictionaryapp.network.parseJsonToWord
import kotlinx.coroutines.launch

// Enum class representing the status of DictionaryApi requests
enum class DictionaryApiStatus { LOADING, ERROR, DONE }

// Base URL for dictionary word images
private const val BASE_IMAGE_URL = "https://www.merriam-webster.com/assets/mw/static/art/dict/"

// Define the DictionaryViewViewModel class, extending ViewModel
class DictionaryViewViewModel : ViewModel() {

    // MutableLiveData for tracking the status of DictionaryApi requests
    private val _apiStatus = MutableLiveData<DictionaryApiStatus>()
    val apiStatus: LiveData<DictionaryApiStatus> = _apiStatus

    // MutableLiveData for storing a list of words
    private val _words = MutableLiveData<List<String>>()
    val words: LiveData<List<String>> = _words

    // MutableLiveData for storing a selected word
    private val _word = MutableLiveData<Word>()
    val word: LiveData<Word> = _word

    // TODO: Necessary or not? (Unused LiveData for status)
    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    // Initialization block for resetting selected word and word list
    init {
        resetSelectedWord()
        resetWordList()
    }

    // Method to make a DictionaryApi request for a word
    fun getWordResponse(searchWord: String) {

        viewModelScope.launch {
            // Set loading status before making the request
            _apiStatus.value = DictionaryApiStatus.LOADING
            try {
                // Make the DictionaryApi request
                val response = DictionaryApi.retrofitService.getWord(searchWord)
                val jsonString = response.body().toString()

                // Check the format of the JSON response and parse accordingly
                if (jsonString.startsWith("[{")) {
                    _word.value = parseJsonToWord(searchWord, jsonString)
                    _words.value = listOf(word.value?.id.toString())
                } else if (jsonString.startsWith("[")) {
                    var parsedWords = parseJsonStringToListOfWords(jsonString)
                    if (parsedWords.isEmpty()) {
                        parsedWords = parsedWords.toMutableList()
                        parsedWords.add("No matching words found!")
                    } else if (jsonString.startsWith("[]")) {
                        parsedWords = listOf("No matching words found!")
                    }
                    _words.value = parsedWords
                }
                // Set the status to done after a successful request
                _apiStatus.value = DictionaryApiStatus.DONE
            } catch (e: Exception) {
                // Set error status if there is an exception
                _apiStatus.value = DictionaryApiStatus.ERROR
                _words.value = listOf()
            }
        }
    }

    // Method to reset the list of words
    fun resetWordList() {
        _words.value = emptyList()
    }

    // Method to reset the selected word
    fun resetSelectedWord() {
        _word.value = Word("", "", 0, "", false)
    }

    // Method to set a status value (TODO: Unused, consider removing)
    fun setStatus(status: Boolean) {
        _status.value = status
    }

    // Method to get the current selected word
    fun getWord(): Word {
        return word.value!!
    }

    // Method to set a new selected word
    fun setWord(word: Word) {
        _word.value = word
    }

    // Method to handle a click event on a word
    fun onWordClicked(word: String) {
        getWordResponse(word)
    }

    // Method to get the base URL for word images
    fun getURL(): String {
        return BASE_IMAGE_URL
    }
}
