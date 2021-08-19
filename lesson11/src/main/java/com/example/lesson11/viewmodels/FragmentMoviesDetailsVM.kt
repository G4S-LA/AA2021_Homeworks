package com.example.lesson11.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson11.data.MovieRepositoryImpl
import com.example.lesson11.data.db.entities.MovieDetailsEntity
import kotlinx.coroutines.launch
class FragmentMoviesDetailsVM : ViewModel() {
    lateinit var movieDetailsLiveData: LiveData<MovieDetailsEntity>

    fun loadDetails(movieId: Int) {
        movieDetailsLiveData = MovieRepositoryImpl.getLocalMovieDetails(movieId)
        viewModelScope.launch { MovieRepositoryImpl.loadMovieDetails(movieId) }
    }
}