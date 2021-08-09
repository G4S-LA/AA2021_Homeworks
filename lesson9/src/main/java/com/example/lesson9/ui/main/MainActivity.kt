package com.example.lesson9.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson9.R
import com.example.lesson9.background.SyncMoviesImpl
import com.example.lesson9.background.SyncMoviesWorker
import com.example.lesson9.ui.movieslist.FragmentMoviesList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, FragmentMoviesList())
                .commit()
        }
    }
    override fun onStop() {
        super.onStop()

        SyncMoviesWorker.sync(applicationContext)
    }
}