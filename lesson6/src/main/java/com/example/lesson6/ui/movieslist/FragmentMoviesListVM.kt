package com.example.lesson6.ui.movieslist

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

    fun makeApiCall() {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            moviesListLiveData.postValue(MovieRepositoryImpl.loadMovies())
        }
    }
}