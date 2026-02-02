package com.canaldothiago.mybooks.repository

import com.canaldothiago.mybooks.entity.BookEntity

class BookRepository {

    private val books = mutableListOf<BookEntity>()

    init {
        books.addAll(getInitialBooks())
    }

    private fun getInitialBooks(): List<BookEntity> {
        return listOf(
            BookEntity(id = 1, title = "O Senhor dos Anéis", author = "J.R.R. Tolkien", favorite = true, genre = "Fantasia"),
            BookEntity(id = 2, title = "Orgulho e Preconceito", author = "Jane Austen", favorite = false, genre = "Romance"),
            BookEntity(id = 3, title = "Duna", author = "Frank Herbert", favorite = true, genre = "Ficção Científica"),
            BookEntity(id = 4, title = "O Assassinato no Expresso do Oriente", author = "Agatha Christie", favorite = false, genre = "Mistério"),
            BookEntity(id = 5, title = "A Garota no Trem", author = "Paula Hawkins", favorite = false, genre = "Suspense"),
            BookEntity(id = 6, title = "It: A Coisa", author = "Stephen King", favorite = true, genre = "Terror"),
            BookEntity(id = 7, title = "Steve Jobs", author = "Walter Isaacson", favorite = false, genre = "Biografia"),
            BookEntity(id = 8, title = "Sapiens: Uma Breve História da Humanidade", author = "Yuval Noah Harari", favorite = true, genre = "História"),
            BookEntity(id = 9, title = "O Poder do Hábito", author = "Charles Duhigg", favorite = false, genre = "Autoajuda"),
            BookEntity(id = 10, title = "Antologia Poética", author = "Vinícius de Moraes", favorite = true, genre = "Poesia")
        )
    }

    fun getAllBooks(): List<BookEntity> {
        return books
    }

    fun getBookById(id: Int): BookEntity? {
        return books.find { it.id == id }
    }

    fun addBook(book: BookEntity) {
        books.add(book)
    }

    fun deleteBookById(id: Int): Boolean {
        return books.removeIf { it.id == id }
    }

    fun getFavoriteBooks(): List<BookEntity> {
        return books.filter { it.favorite }
    }

    fun toggleFavoriteStatus(id: Int) {
        return books.find { it.id == id }.let {
            it?.favorite = !it.favorite
        }
    }
}