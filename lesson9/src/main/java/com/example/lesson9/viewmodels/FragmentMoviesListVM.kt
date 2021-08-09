package com.example.lesson9.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson9.data.MovieRepositoryImpl
import com.example.lesson9.data.db.entities.MovieEntity
import com.example.lesson9.model.*
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