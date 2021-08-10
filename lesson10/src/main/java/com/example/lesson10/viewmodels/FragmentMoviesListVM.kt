package com.example.lesson10.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson10.data.MovieRepositoryImpl
import com.example.lesson10.data.db.entities.MovieEntity
import com.example.lesson10.model.*
import kotlinx.coroutines.launch

class FragmentMoviesListVM : ViewModel() {
    val moviesListLiveData: LiveData<List<MovieEntity>> = MovieRepositoryImpl.localMovies

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            MovieRepositoryImpl.loadMovies()
        }
    }


}