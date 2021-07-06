package com.example.lesson5.ui.movieslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson5.adapters.MoviesAdapter
import com.example.lesson5.R
import com.example.lesson5.adapters.SpacesItemDecoration
import com.example.lesson5.model.Movie
import com.example.lesson5.ui.moviesdetails.FragmentMoviesDetails

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {
    private val viewModelFragmentVM: FragmentMoviesListVM by lazy {
        ViewModelProvider(this).get(FragmentMoviesListVM::class.java)
    }

    companion object { const val MOVIE = "movie" }

    private val moviesAdapter: MoviesAdapter by lazy { MoviesAdapter(requireContext(), movieDetails) }

    private val rvMovies: RecyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.rv_movies).apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(SpacesItemDecoration(20))
        }
    }

    private val movieDetails = object : MoviesAdapter.OnMovieListener {
        override fun onClickMovie(movie: Movie) {

            val fragment = FragmentMoviesDetails()
            fragment.arguments = Bundle().apply { putSerializable(MOVIE, movie) }
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.main, fragment)
                .addToBackStack(null).commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMovies.adapter = moviesAdapter
        viewModelFragmentVM.makeApiCall()
        viewModelFragmentVM.moviesListLiveData.observe(viewLifecycleOwner) {
            moviesAdapter.refresh(it ?: return@observe)
        }
    }

}
