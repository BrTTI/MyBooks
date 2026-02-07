package com.canaldothiago.mybooks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canaldothiago.mybooks.entity.BookEntity
import com.canaldothiago.mybooks.repository.BookRepository

class FavoriteViewModel: ViewModel() {

    private val _books = MutableLiveData<List<BookEntity>>()
    val books: LiveData<List<BookEntity>> = _books
    private val repository = BookRepository.getInstance()
    fun getFavoriteBooks() {
        _books.value = repository.getFavoriteBooks()
    }

    fun favorite(id: Int) {
        repository.toggleFavoriteStatus(id)
    }
}