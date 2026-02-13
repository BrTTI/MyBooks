package com.canaldothiago.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.canaldothiago.mybooks.data.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDAO {

    @Query("SELECT * FROM Book")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM Book WHERE favorite = 1")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM Book WHERE id = :id")
    suspend fun getBookById(id: Int): BookEntity

    @Delete
    suspend fun delete(book: BookEntity): Int

    @Update
    suspend fun update(book: BookEntity)

    @Insert
    suspend fun insert(book: List<BookEntity>)
}