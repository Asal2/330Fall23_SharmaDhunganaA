package com.example.worddictionaryapp.network

import com.example.worddictionaryapp.database.Word
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
    val shortDef = entry.getJSONArray("shortdef")
    var image: String? = null

    if (entry.has("art")) {
        image = entry.getJSONObject("art").getString("artid")
    }

    val word = when (shortDef.length()) {
        0 -> Word(wordId, "No definition available")
        1 -> Word(wordId, shortDef.getString(0), imageName = image)
        2 -> Word(wordId, shortDef.getString(0), shortDef.getString(1), imageName = image)
        else -> Word(
            wordId, shortDef.getString(0),
            shortDef.getString(1),
            shortDef.getString(2),
            imageName = image
        )
    }
    return word
}