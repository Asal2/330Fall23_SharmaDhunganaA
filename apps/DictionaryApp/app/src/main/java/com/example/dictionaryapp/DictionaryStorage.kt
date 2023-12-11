// Import necessary packages and classes
package com.example.dictionaryapp

import androidx.annotation.WorkerThread
import com.example.dictionaryapp.database.Word
import com.example.dictionaryapp.database.WordDao
import kotlinx.coroutines.flow.Flow

// Define the DictionaryStorage class to abstract data operations
class DictionaryStorage(private val wordDao: WordDao) {

    // Flow representing a list of all words in the database
    val allWords: Flow<List<Word>> = wordDao.getAll()

    // Flow representing a list of active words in the database
    val activeWords: Flow<List<Word>> = wordDao.getActive()

    // Flow representing a list of inactive words in the database
    val inactiveWords: Flow<List<Word>> = wordDao.getInactive()

    // Function to check the count of a specific word in the database
    fun checkWordCount(word: String): Flow<Int> {
        return wordDao.checkWordExists(word)
    }

    // Function to activate a specific word in the database
    fun activate(word: String) {
        wordDao.activate(word)
    }

    // Function to deactivate a specific word in the database
    fun deactivate(word: String) {
        wordDao.deactivate(word)
    }

    // Suspend function to insert a new word into the database in a worker thread
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}
