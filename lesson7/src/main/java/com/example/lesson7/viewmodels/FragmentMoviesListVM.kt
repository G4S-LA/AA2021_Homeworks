package com.example.lesson7.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson7.data.MovieRepositoryImpl
import com.example.lesson7.model.*
import kotlinx.coroutines.launch

class FragmentMoviesListVM : ViewModel() {
    var moviesListLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
        private set

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            val movies = MovieRepositoryImpl.loadMovies()

            handleResult(movies)
        }
    }

    private fun handleResult(result: Result<List<Movie>>) {
        when (result) {
            is Success -> moviesListLiveData.postValue(result.data)
            is Failure -> moviesListLiveData.postValue(emptyList())
        }
    }
}