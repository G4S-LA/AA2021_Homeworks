package com.example.lesson6.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson6.data.MovieRepositoryImpl
import com.example.lesson6.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FragmentMoviesListVM : ViewModel() {
    var moviesListLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
        private set

    fun loadMovies() {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            moviesListLiveData.postValue(MovieRepositoryImpl.loadMovies())
        }
    }
}