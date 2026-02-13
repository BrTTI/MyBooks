package com.canaldothiago.mybooks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.canaldothiago.mybooks.data.entity.BookEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [BookEntity::class], version = 1)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDAO(): BookDAO
    companion object {
        private lateinit var instance: BookDatabase
        const val DATABASE_NAME = "BookDB.db"

        fun getDatabase(context: Context): BookDatabase {
            if (!::instance.isInitialized) {
                synchronized(this) {
                    instance =
                        Room.databaseBuilder(context, BookDatabase::class.java, DATABASE_NAME)
                            .addMigrations(Migrations.MIGRATION_1_2)
                            .addCallback(DatabaseCallback(context))
                            // .allowMainThreadQueries()
                            .build()
                }
            }
            return instance
        }
    }

    private class DatabaseCallback(val context: Context): Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                getDatabase(context).bookDAO().insert(getInitialBooks())
            }
        }

        /**
         * Cria uma lista inicial de livros para popular o banco de dados.
         * */
        private fun getInitialBooks(): List<BookEntity> {
            return listOf(
                BookEntity(1, "To Kill a Mockingbird", "Harper Lee", false, "Ficção"),
                BookEntity(2, "Dom Casmurro", "Machado de Assis", false, "Romance"),
                BookEntity(3, "O Hobbit", "J.R.R. Tolkien", true, "Fantasia"),
                BookEntity(4, "Cem Anos de Solidão", "Gabriel García Márquez", false, "Romance"),
                BookEntity(5, "O Pequeno Príncipe", "Antoine de Saint-Exupéry", false, "Fantasia"),
                BookEntity(6, "Crime e Castigo", "Fiódor Dostoiévski", false, "Ficção policial"),
                BookEntity(7, "Frankenstein", "Mary Shelley", false, "Terror"),
                BookEntity(8, "Harry Potter e a Pedra Filosofal", "J.K. Rowling", false, "Fantasia"),
                BookEntity(9, "Neuromancer", "William Gibson", false, "Cyberpunk"),
                BookEntity(10, "Senhor dos Anéis", "J.R.R. Tolkien", false, "Fantasia"),
                BookEntity(11, "Drácula", "Bram Stoker", false, "Terror"),
                BookEntity(12, "Orgulho e Preconceito", "Jane Austen", false, "Romance"),
                BookEntity(13, "Harry Potter e a Câmara Secreta", "J.K. Rowling", false, "Fantasia"),
                BookEntity(14, "As Crônicas de Nárnia", "C.S. Lewis", false, "Fantasia"),
                BookEntity(15, "O Código Da Vinci", "Dan Brown", false, "Mistério"),
                BookEntity(16, "It: A Coisa", "Stephen King", false, "Terror"),
                BookEntity(17, "Moby Dick", "Herman Melville", false, "Aventura"),
                BookEntity(18, "O Nome do Vento", "Patrick Rothfuss", false, "Fantasia"),
                BookEntity(19, "O Conde de Monte Cristo", "Alexandre Dumas", false, "Aventura"),
                BookEntity(20, "Os Miseráveis", "Victor Hugo", false, "Romance")
            )
        }

    }

    private object Migrations {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                super.migrate(db)
                db.execSQL("DELETE TABLE Book")
            }
        }
    }
}