package com.example.lesson5

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.lesson5.data.JsonMovieRepository
import kotlinx.serialization.json.JsonContentPolymorphicSerializer

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            Repository.init(this)
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, FragmentMoviesList())
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        Repository.release()
    }
}

object Repository {

    @SuppressLint("StaticFieldLeak")
    var jsonMovieRepository: JsonMovieRepository? = null

    fun init(context: Context) {
        jsonMovieRepository = JsonMovieRepository()
        jsonMovieRepository?.init(context)
    }

    fun release() {
        jsonMovieRepository = null
    }
}