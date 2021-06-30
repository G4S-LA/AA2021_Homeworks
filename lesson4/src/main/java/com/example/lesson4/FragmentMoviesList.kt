package com.example.lesson4

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {
    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(requireContext(), generateMovies(requireContext()), movieDetails)
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
    }

}
