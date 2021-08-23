package com.example.lesson11.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson11.data.MovieRepositoryImpl
import com.example.lesson11.data.db.entities.MovieDetailsEntity
import kotlinx.coroutines.launch

class FragmentMoviesDetailsVM : ViewModel() {
    val movieDetailsLiveData: MutableLiveData<MovieDetailsEntity> = MutableLiveData()

    fun loadDetails(movieId: Int) {
        viewModelScope.launch {
            MovieRepositoryImpl.loadMovieDetails(movieId)
            movieDetailsLiveData.postValue(
                MovieRepositoryImpl.getLocalMovieDetails(movieId)
            )
        }
    }
}