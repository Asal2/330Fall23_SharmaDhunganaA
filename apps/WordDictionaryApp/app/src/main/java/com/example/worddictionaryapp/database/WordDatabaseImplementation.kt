package com.example.worddictionaryapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDatabaseDao {

    @Insert
    suspend fun insertWord(word: Word)

    @Update
    suspend fun updateWord(word: Word)

    @Query("DELETE FROM word_dictionary")
    suspend fun clear()

    @Query("SELECT exists(select * from word_dictionary WHERE word_id = :id)")
    suspend fun wordExists(id: String): Boolean

    @Query("SELECT * FROM word_dictionary ORDER BY word_id DESC")
    fun getAllWords(): LiveData<List<Word>>

    @Query("SELECT * FROM word_dictionary where active = 1 ORDER BY word_id DESC")
    fun getActiveWords(): LiveData<List<Word>>

    @Query("SELECT * FROM word_dictionary where active = 0 ORDER BY word_id DESC")
    fun getInactiveWords(): LiveData<List<Word>>
}