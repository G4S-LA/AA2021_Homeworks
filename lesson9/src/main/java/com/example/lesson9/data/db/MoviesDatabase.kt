package com.example.lesson9.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lesson9.data.db.entities.GenreEntity
import com.example.lesson9.data.db.entities.MovieDetailsEntity
import com.example.lesson9.data.db.entities.MovieEntity


@Database(entities = [MovieEntity::class, MovieDetailsEntity::class, GenreEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    "MovieDB"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                instance
            }
        }
    }
}