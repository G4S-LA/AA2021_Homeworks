package com.example.lesson5

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson5.data.JsonMovieRepository
import com.example.lesson5.data.MovieRepository
import com.example.lesson5.model.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MoviesViewModel: ViewModel() {
    var moviesListLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    private lateinit var repository: MovieRepository

    fun getMoviesListObserver(): MutableLiveData<List<Movie>> {
        return moviesListLiveData
    }

    fun setRepository(repository: MovieRepository) {
        this.repository = repository
    }

    fun makeApiCall() {
        viewModelScope.launch {
            moviesListLiveData.postValue(repository.loadMovies())
        }
    }
}