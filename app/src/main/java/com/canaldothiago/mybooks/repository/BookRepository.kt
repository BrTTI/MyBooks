package com.canaldothiago.mybooks.repository

import android.content.Context
import com.canaldothiago.mybooks.data.BookDatabase
import com.canaldothiago.mybooks.data.entity.BookEntity
import kotlinx.coroutines.flow.Flow

/**
 * Classe responsável por armazenar e manipular os livros.
 * Simula um banco de dados local usando uma lista mutável.
 */
class BookRepository private constructor(context: Context) {

    private var database = BookDatabase.getDatabase(context).bookDAO()

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
    fun getAllBooks(): Flow<List<BookEntity>> {
        return database.getAllBooks()
    }

    /**
     * Retorna todos os livros marcados como favoritos.
     */
    fun getFavoriteBooks(): Flow<List<BookEntity>> {
        return database.getFavoriteBooks()
    }

    /**
     * Busca um livro pelo ID.
     */
    suspend fun getBookById(id: Int): BookEntity {
        return database.getBookById(id)
    }

    /**
     * Alterna entre true e false o atributo 'favorite'
     * */
    suspend fun toggleFavoriteStatus(id: Int) {
        val book = getBookById(id)
        book.favorite = !book.favorite
        database.update(book)
    }

    /**
     * Remove um livro pelo ID.
     */
    suspend fun deleteBookById(id: Int): Boolean {
        return database.delete(getBookById(id)) > 0
    }
}