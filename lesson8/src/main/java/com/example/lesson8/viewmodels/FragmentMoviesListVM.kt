package com.example.lesson8.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson8.data.MovieRepositoryImpl
import com.example.lesson8.model.*
import kotlinx.coroutines.launch

class FragmentMoviesListVM : ViewModel() {
    val moviesListLiveData: LiveData<List<Movie>> = MovieRepositoryImpl.localMovies

    init {
        //loadMovies()
        viewModelScope.launch {
            MovieRepositoryImpl.insert()
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            MovieRepositoryImpl.loadMovies()
        }
    }
}