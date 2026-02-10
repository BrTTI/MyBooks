package com.canaldothiago.mybooks.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.canaldothiago.mybooks.entity.BookEntity
import com.canaldothiago.mybooks.helper.DatabaseConstants


/**
 * Classe responsável por armazenar e manipular os livros.
 * Simula um banco de dados local usando uma lista mutável.
 */
class BookRepository private constructor(context: Context) {

    private var database = BookDatabaseHelper(context)

    /**
     * O padrão Singleton garante que uma classe tenha apenas uma instância durante toda a execução do programa.
     * Ele fornece um ponto de acesso global para acessar essa instância de forma controlada e segura.
     *
     * Vantagens do Singleton:
     * - Garante que a classe tenha uma única instância.
     * - Oferece um ponto de acesso global para essa instância.
     * - Pode ser útil para recursos compartilhados, como conexões de banco de dados ou repositórios de dados.
     */
    companion object {
        private lateinit var instance: BookRepository

        /**
         * Fornece a única instância do BookRepository.
         * Esta é uma implementação thread-safe do padrão singleton.
         */
        fun getInstance(context: Context): BookRepository {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = BookRepository(context)
                }
            }
            return instance
        }
    }

    /**
     * Retorna todos os livros armazenados.
     */
    fun getAllBooks(): List<BookEntity> {
        val list = mutableListOf<BookEntity>()
        val db = database.readableDatabase
        val cursor = db.query(
            DatabaseConstants.BOOK.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            list.add(cursorToBook(cursor))
        }

        cursor.close()
        db.close()
        return list
    }

    /**
     * Busca um livro pelo ID.
     */
    fun getBookById(id: Int): BookEntity? {
        val db = database.readableDatabase
        var book: BookEntity? = null
        val cursor = db.query(
            DatabaseConstants.BOOK.TABLE_NAME,
            null,
            "${DatabaseConstants.BOOK.COLUMNS.ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        if (cursor.moveToFirst()) {
            book = cursorToBook(cursor)
        }

        cursor.close()
        db.close()
        return book
    }

    /**
     * Remove um livro pelo ID.
     */
    fun deleteBookById(id: Int): Int {
        val db = database.readableDatabase
        val rowsDeleted = db.delete(
            DatabaseConstants.BOOK.COLUMNS.ID,
            "${DatabaseConstants.BOOK.COLUMNS.ID} = ?",
            arrayOf(id.toString())
        )
        db.close()
        return rowsDeleted
    }

    /**
     * Retorna todos os livros marcados como favoritos.
     */
    fun getFavoriteBooks(): List<BookEntity> {
        val db = database.readableDatabase
        val list = mutableListOf<BookEntity>()
        val cursor = db.query(
            DatabaseConstants.BOOK.TABLE_NAME,
            null,
            "${DatabaseConstants.BOOK.COLUMNS.FAVORITE} = ?",
            arrayOf("1"),
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            list.add(cursorToBook(cursor))
        }

        cursor.close()
        db.close()
        return list
    }

    /**
     * Alterna entre true e false o atributo 'favorite'
     * */
    fun toggleFavoriteStatus(id: Int, currentStats: Boolean) {
        val db = database.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseConstants.BOOK.COLUMNS.FAVORITE, if (currentStats) 0 else 1)
        }
        db.update(
            DatabaseConstants.BOOK.TABLE_NAME,
            values,
            "${DatabaseConstants.BOOK.COLUMNS.ID} = ?",
            arrayOf(id.toString())
        )
        db.close()
    }

    private fun cursorToBook(cursor: Cursor): BookEntity {
        return BookEntity(
            id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.ID)),
            title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.TITLE)),
            author = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.AUTHOR)),
            genre = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.GENRE)),
            favorite = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.FAVORITE)) == 1
        )
    }
}