package com.canaldothiago.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canaldothiago.mybooks.entity.BookEntity
import com.canaldothiago.mybooks.repository.BookRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _books = MutableLiveData<List<BookEntity>>()
    val books: LiveData<List<BookEntity>> = _books
    private val repository = BookRepository.getInstance(application.applicationContext)
    fun getAllBooks() {
        _books.value = repository.getAllBooks()
    }
    fun favorite(id: Int) {
        repository.toggleFavoriteStatus(id)
    }
}