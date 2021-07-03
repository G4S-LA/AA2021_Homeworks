package com.example.lesson5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.lesson5.data.JsonMovieRepository
import kotlinx.serialization.json.JsonContentPolymorphicSerializer

class MainActivity : AppCompatActivity() {
    private val viewModel: MoviesViewModel by lazy {
        ViewModelProvider(this).get(MoviesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            viewModel.setRepository(JsonMovieRepository(this))
            viewModel.makeApiCall()

            supportFragmentManager.beginTransaction()
                    .add(R.id.main_container, FragmentMoviesList(viewModel))
                    .commit()
        }
    }
}
