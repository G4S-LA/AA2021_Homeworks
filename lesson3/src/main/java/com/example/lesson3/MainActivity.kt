package com.example.lesson3


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lesson2.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.main_container, FragmentMoviesList())
                    .commit()
        }

        val cardFilm = findViewById<View>(R.id.main_container)
        cardFilm.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .add(R.id.main, FragmentMoviesDetails())
                    .addToBackStack(null)
                    .commit()
        }
    }
}