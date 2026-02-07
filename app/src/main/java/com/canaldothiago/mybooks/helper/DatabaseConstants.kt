package com.canaldothiago.mybooks.helper

class DatabaseConstants private constructor() {

    object BOOK {
        const val TABLE_NAME = "books"

        object COLUMNS {
            const val ID = "id"
            const val TITLE = "title"
            const val AUTHOR = "author"
            const val FAVORITE = "favorite"
            const val GENRE = "genre"
        }
    }
}