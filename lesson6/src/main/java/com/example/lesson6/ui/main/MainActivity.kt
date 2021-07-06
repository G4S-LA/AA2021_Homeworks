package com.example.lesson6.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson6.R
import com.example.lesson6.data.MovieRepositoryImpl
import com.example.lesson6.ui.movieslist.FragmentMoviesList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            MovieRepositoryImpl.init(this)
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, FragmentMoviesList())
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        MovieRepositoryImpl.release()
    }
}