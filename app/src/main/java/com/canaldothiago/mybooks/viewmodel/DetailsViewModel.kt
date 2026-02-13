package com.canaldothiago.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.canaldothiago.mybooks.data.entity.BookEntity
import com.canaldothiago.mybooks.repository.BookRepository
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = BookRepository.getInstance(application.applicationContext)
    private val _book = MutableLiveData<BookEntity>()
    val book: LiveData<BookEntity> = _book
    private val _bookDeleted = MutableLiveData<Boolean>()
    val bookDeleted: LiveData<Boolean> = _bookDeleted

    fun getBook(id: Int) {
        viewModelScope.launch { _book.value = repository.getBookById(id) }
    }

    fun toggleFavorite(id: Int) {
        viewModelScope.launch { repository.toggleFavoriteStatus(id) }
    }

    fun deleteBook(id: Int) {
        viewModelScope.launch { _bookDeleted.value = repository.deleteBookById(id) }
    }
}