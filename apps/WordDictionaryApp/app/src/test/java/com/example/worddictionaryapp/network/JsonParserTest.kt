package com.example.worddictionaryapp.network



import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test


class JsonParserTest {
    @Test
    fun parse_response_with_one_shortdef_string() {
        // Given
        val jsonString = javaClass.getResource("/word_with_one_def_response.json")?.readText()

        // When
        val word = parseJsonToWord("baseball", jsonString!!)

        // Then
        MatcherAssert.assertThat(word.id, CoreMatchers.`is`("baseball"))
        MatcherAssert.assertThat(
            word.shortdef1,
            CoreMatchers.startsWith("a game played with a bat and ball between")
        )
        MatcherAssert.assertThat(
            word.shortdef1,
            CoreMatchers.endsWith("also : the ball used in this game")
        )
        MatcherAssert.assertThat(word.shortdef2, CoreMatchers.`is`(""))
        MatcherAssert.assertThat(word.shortdef3, CoreMatchers.`is`(""))
    }

    @Test
    fun parse_response_with_art() {
        // Given
        val jsonString = javaClass.getResource("/word_with_art_response.json")?.readText()

        // When
        val word = parseJsonToWord("moose", jsonString!!)

        // Then
        MatcherAssert.assertThat(word.id, CoreMatchers.`is`("moose"))
        MatcherAssert.assertThat(word.imageName, CoreMatchers.`is`("moose"))
    }

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