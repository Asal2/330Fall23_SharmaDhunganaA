package com.example.dictionaryapp

import androidx.annotation.WorkerThread
import com.example.dictionaryapp.database.Word
import com.example.dictionaryapp.database.WordDao
import kotlinx.coroutines.flow.Flow

class DictionaryStorage(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAll()

    val activeWords: Flow<List<Word>> = wordDao.getActive()

    val inactiveWords: Flow<List<Word>> = wordDao.getInactive()

    fun checkWordCount(word: String): Flow<Int> {
        return wordDao.checkWordExists(word)
    }

    fun activate(word: String){
        wordDao.activate(word)
    }

    fun deactivate(word: String){
        wordDao.deactivate(word)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}