package com.example.lesson6.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson6.data.MovieRepositoryImpl
import com.example.lesson6.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FragmentMoviesDetailsVM : ViewModel() {
    var moviesListLiveData: MutableLiveData<Movie> = MutableLiveData()
        private set

    fun loadDetails(id: Int) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            moviesListLiveData.postValue(MovieRepositoryImpl.loadMovie(id))
        }
    }
}