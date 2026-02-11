package com.canaldothiago.mybooks.repository

import android.content.Context
import com.canaldothiago.mybooks.entity.BookEntity

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
    fun getAllBooks(): List<BookEntity> {
        return database.getAllBooks()
    }

    /**
     * Busca um livro pelo ID.
     */
    fun getBookById(id: Int): BookEntity {
        return database.getBookById(id)
    }

    /**
     * Retorna todos os livros marcados como favoritos.
     */
    fun getFavoriteBooks(): List<BookEntity> {
        return database.getFavoriteBooks()
    }

    /**
     * Alterna entre true e false o atributo 'favorite'
     * */
    fun toggleFavoriteStatus(id: Int) {
        val book = getBookById(id)
        book.favorite = !book.favorite
        database.update(book)
    }

    /**
     * Remove um livro pelo ID.
     */
    fun deleteBookById(id: Int): Boolean {
        return database.delete(getBookById(id)) > 0
    }
}