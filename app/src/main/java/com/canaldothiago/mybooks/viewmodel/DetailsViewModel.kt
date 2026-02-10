package com.canaldothiago.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canaldothiago.mybooks.entity.BookEntity
import com.canaldothiago.mybooks.repository.BookRepository

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = BookRepository.getInstance(application.applicationContext)
    private val _book = MutableLiveData<BookEntity>()
    val book: LiveData<BookEntity> = _book
    private val _bookRemoved = MutableLiveData<Int>()
    val bookRemoved: LiveData<Int> = _bookRemoved
    private val _favorite = MutableLiveData<Boolean>()
    val favorite: LiveData<Boolean> = _favorite

    fun getBookById(id: Int) {
        _book.value = repository.getBookById(id)
    }

    fun toggleFavorite(id: Int) {
        val currentStats = _book.value?.favorite ?: false
        repository.toggleFavoriteStatus(id, currentStats)
        getBookById(id)
    }

    fun removeBook(id: Int) {
        _bookRemoved.value = repository.deleteBookById(id)
    }
}