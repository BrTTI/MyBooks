package com.canaldothiago.mybooks.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Book")
class BookEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("author")
    val author: String,

    @ColumnInfo("favorite")
    var favorite: Boolean,

    @ColumnInfo("genre")
    val genre: String
)