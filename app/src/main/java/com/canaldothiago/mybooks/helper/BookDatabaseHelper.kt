package com.canaldothiago.mybooks.helper
//
//import android.content.ContentValues
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//import com.canaldothiago.mybooks.entity.BookEntity
//import com.canaldothiago.mybooks.helper.DatabaseConstants
//
//class BookDatabaseHelper(context: Context) :
//    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
//
//    override fun onCreate(db: SQLiteDatabase) {
//        db.execSQL(CREATE_TABLE_BOOKS)
//        insertBooksIntoDB(db)
//    }
//
//    override fun onUpgrade(
//        db: SQLiteDatabase,
//        oldVersion: Int,
//        newVersion: Int
//    ) {
//        TODO("Not yet implemented")
//    }
//
//    companion object {
//        const val DATABASE_NAME = "BookDB"
//        const val DATABASE_VERSION = 1
//        const val CREATE_TABLE_BOOKS = """
//            CREATE TABLE IF NOT EXISTS ${DatabaseConstants.BOOK.TABLE_NAME} (
//                ${DatabaseConstants.BOOK.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
//                ${DatabaseConstants.BOOK.COLUMNS.TITLE} TEXT NOT NULL,
//                ${DatabaseConstants.BOOK.COLUMNS.AUTHOR} TEXT NOT NULL,
//                ${DatabaseConstants.BOOK.COLUMNS.GENRE} TEXT NOT NULL,
//                ${DatabaseConstants.BOOK.COLUMNS.FAVORITE} INTEGER NOT NULL
//                )
//        """
//    }
//
//    private fun insertBooksIntoDB(db: SQLiteDatabase) {
//        val books = getInitialBooks()
//        for (book in books) {
//            val values = ContentValues().apply {
//                put(DatabaseConstants.BOOK.COLUMNS.TITLE, book.title)
//                put(DatabaseConstants.BOOK.COLUMNS.AUTHOR, book.author)
//                put(DatabaseConstants.BOOK.COLUMNS.GENRE, book.genre)
//                put(DatabaseConstants.BOOK.COLUMNS.FAVORITE, if (book.favorite) 1 else 0)
//            }
//            db.insert(DatabaseConstants.BOOK.TABLE_NAME, null, values)
//        }
//    }
//}