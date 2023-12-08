package com.example.worddictionaryapp.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "word_dictionary")
data class Word(
    @PrimaryKey @ColumnInfo (name="word_id") val id: String,
    @ColumnInfo(name = "shortdef1") val shortdef1: String,
    @ColumnInfo(name = "shortdef2") val shortdef2: String? = null,
    @ColumnInfo(name = "shortdef3") val shortdef3: String? = null,
    @ColumnInfo(name = "image_name") val imageName: String? = null,
    @ColumnInfo(name = "active") val active: Boolean = true
)