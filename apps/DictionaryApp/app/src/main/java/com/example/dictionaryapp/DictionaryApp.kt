package com.example.dictionaryapp

import android.app.Application
import com.example.dictionaryapp.database.WordDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class DictionaryApp : Application(){
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { WordDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { DictionaryStorage(database.wordDao()) }
}