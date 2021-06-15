package com.example.lesson3


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson2.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.main_container, FragmentMoviesDetails())
                .commit()
    }
}