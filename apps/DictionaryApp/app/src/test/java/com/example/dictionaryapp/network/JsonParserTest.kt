package com.example.dictionaryapp.network

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test


class JsonParserTest {

    @Test
    fun parse_json_to_string_list() {
        // Given
        val jsonString = javaClass.getResource("/string_array_response.json")?.readText()

        // When
        val wordList = parseJsonStringToListOfWords(jsonString!!)

        // Then
        MatcherAssert.assertThat(wordList.size, CoreMatchers.`is`(4))
    }

    @Test
    fun parse_json_to_string_list_when_empty_json_array() {
        // Given
        val jsonString = "[]"

        // When
        val wordList = parseJsonStringToListOfWords(jsonString!!)

        // Then
        MatcherAssert.assertThat(wordList.size, CoreMatchers.`is`(0))
    }
}