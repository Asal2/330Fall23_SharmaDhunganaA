package com.example.dictionaryapp.network


import com.example.dictionaryapp.database.Word
import org.json.JSONArray


fun parseJsonStringToListOfWords(jsonStr: String): List<String> {
    val jsonArrayObj = JSONArray(jsonStr)
    val wordList = mutableListOf<String>()
    for(i in 0 until jsonArrayObj.length()) {
        wordList.add(i, jsonArrayObj.getString(i))
    }
    return wordList
}


fun parseJsonToWord(wordId: String, jsonStr: String) : Word {
    val json = JSONArray(jsonStr)
    // Gets the first entry in the array
    val entry = json.getJSONObject(0)
    val shortDefArr = entry.getJSONArray("shortdef")
    var image: String? = null
    var shortdefs = mutableListOf<String>()

    if (entry.has("art")) {
        image = entry.getJSONObject("art").getString("artid")
    }

    for (j in 0 until shortDefArr.length()){
        shortdefs.add(j,shortDefArr.getString(j))
    }

    val word = when(shortDefArr.length()){
        0 -> Word(wordId, "No definition available", 0, image)
        else -> Word(wordId, shortdefs.joinToString(separator = ";"), shortDefArr.length(), image)
    }

    return word
}
