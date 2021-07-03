package com.example.lesson5

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewOutlineProvider
import android.view.ViewPropertyAnimator
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson5.data.JsonMovieRepository
import com.example.lesson5.data.MovieRepository
import com.example.lesson5.model.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class FragmentMoviesList(private val viewModel: MoviesViewModel) : Fragment(R.layout.fragment_movies_list) {

    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(requireContext(), listOf(), movieDetails)
    }

    private val rvMovies: RecyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.rv_movies).apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(SpacesItemDecoration(20))
        }
    }

    private val movieDetails = object : MoviesAdapter.OnMovieListener {
        override fun onClickMovie(movie: Movie) {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.main, FragmentMoviesDetails(movie))
                .addToBackStack(null).commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMovies.adapter = moviesAdapter
        viewModel.moviesListLiveData.observe(requireActivity()) {
            moviesAdapter.refresh(viewModel.getMoviesListObserver().value ?: listOf())
        }
    }

}
