package com.example.lesson10.notifications

import android.content.Context
import com.example.lesson10.data.db.entities.MovieEntity

interface Notification {
    fun show(context: Context, movie: MovieEntity?)
}