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

enum class DictionaryApiStatus {LOADING, ERROR, DONE}
private const val BASE_IMAGE_URL = "https://www.merriam-webster.com/assets/mw/static/art/dict/"

class DictionaryViewViewModel: ViewModel() {
    private val _apiStatus = MutableLiveData<DictionaryApiStatus>()
    val apiStatus: LiveData<DictionaryApiStatus> = _apiStatus

    private val _words = MutableLiveData<List<String>>()
    val words: LiveData<List<String>> = _words

    private val _word = MutableLiveData<Word>()
    val word: LiveData<Word> = _word


    //TODO necessary? or no
    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    init {
        resetSelectedWord()
        resetWordList()
    }

    fun getWordResponse(searchWord: String) {

        viewModelScope.launch {
            _apiStatus.value = DictionaryApiStatus.LOADING
            try {
                val response = DictionaryApi.retrofitService.getWord(searchWord)
                val jsonString = response.body().toString()
//                var searchedWords = mutableListOf<String>()
                if (jsonString.startsWith("[{")) {
                    // call your parseJsonToWord function
//                    searchedWords.add(parseJsonToWord(searchWord, jsonString).id)
                    _word.value = parseJsonToWord(searchWord, jsonString)
                    _words.value = listOf(word.value?.id.toString())

                } //else if (jsonString.startsWith("[\"")) {
                else if (jsonString.startsWith("[")){
                    Log.d("ViewViewModel", jsonString.toString())
                    var parsedWords = parseJsonStringToListOfWords(jsonString)
                    if (parsedWords.isEmpty()) {
                        parsedWords = parsedWords.toMutableList()
                        parsedWords.add("No matching words found!")
                    } else if (jsonString.startsWith("[]")) {
                        Log.d("ViewViewModel","no matching word")
                        parsedWords = listOf("No matching words found!")
                    }
                    _words.value = parsedWords
                    Log.d("ViewViewModel words",words.value.toString())
//                    for (i in parsedWords.indices) {
//                        searchedWords.add(i, parsedWords[i])
//                    }
                }
                _apiStatus.value = DictionaryApiStatus.DONE
            } catch (e: Exception) {
                _apiStatus.value = DictionaryApiStatus.ERROR
                _words.value = listOf()
            }

        }
    }

    fun resetWordList(){
        _words.value = emptyList()
    }

    fun resetSelectedWord(){
        _word.value = Word("", "",0,"", false)
    }

    fun setStatus(status: Boolean){
        _status.value = status
    }

    fun getWord() : Word{
        return word.value!!
    }

    fun setWord(word: Word){
        _word.value = word
    }

    fun onWordClicked(word: String){
        getWordResponse(word)
    }

    fun getURL(): String{
        return BASE_IMAGE_URL
    }

}
