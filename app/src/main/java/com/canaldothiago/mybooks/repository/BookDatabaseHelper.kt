package com.canaldothiago.mybooks.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.canaldothiago.mybooks.helper.DatabaseConstants

class BookDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_BOOKS)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }

    companion object {
        const val DATABASE_NAME = "BookDB"
        const val DATABASE_VERSION = 1
        const val CREATE_TABLE_BOOKS = """
            CREATE TABLE IF NOT EXISTS ${DatabaseConstants.BOOK.TABLE_NAME} (
                ${DatabaseConstants.BOOK.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${DatabaseConstants.BOOK.COLUMNS.TITLE} TEXT NOT NULL,
                ${DatabaseConstants.BOOK.COLUMNS.AUTHOR} TEXT NOT NULL,
                ${DatabaseConstants.BOOK.COLUMNS.GENRE} TEXT NOT NULL,
                ${DatabaseConstants.BOOK.COLUMNS.FAVORITE} INTEGER NOT NULL
                )
        """
    }
}