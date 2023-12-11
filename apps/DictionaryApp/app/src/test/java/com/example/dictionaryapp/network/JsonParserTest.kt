package com.example.dictionaryapp.network

import com.example.dictionaryapp.database.Word
import org.junit.Assert
import org.junit.Test
class JsonParserTest {
    @Test
    fun parse_json_array_to_list_of_words(){
        //given
        val jsonString = javaClass.getResource("/string_array_response.json")?.readText()

        //when or act
        val wordList = parseJsonStringToListOfWords(jsonString!!)

        //then or assert
        Assert.assertEquals(4, wordList.size)
    }

    //first case, starting with [{
    @Test
    fun parse_json_to_word(){

        val jsonString = javaClass.getResource("/word_with_two_def_response.json")?.readText()

        //when or act
        val word = parseJsonToWord("bread", jsonString!!)

        //then or assert
        Assert.assertEquals(
            Word("bread",
                "a usually baked and leavened food made of a mixture whose basic constituent is flour or meal;food, sustenance;livelihood",
                3, "", false),word)

    }

}