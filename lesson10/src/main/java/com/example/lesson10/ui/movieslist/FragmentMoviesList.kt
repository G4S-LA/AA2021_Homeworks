package com.example.lesson10.ui.movieslist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson10.R
import com.example.lesson10.adapters.MoviesAdapter
import com.example.lesson10.adapters.SpacesItemDecoration
import com.example.lesson10.model.Movie
import com.example.lesson10.ui.moviesdetails.FragmentMoviesDetails
import com.example.lesson10.viewmodels.FragmentMoviesListVM

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {
    private val viewModelFragmentVM: FragmentMoviesListVM by lazy {
        ViewModelProvider(this).get(FragmentMoviesListVM::class.java)
    }

    companion object { const val MOVIE_ID = "movie_id" }

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
            fragment.arguments = Bundle().apply { putInt(MOVIE_ID, movie.id) }
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.main, fragment)
                .addToBackStack(null).commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMovies.adapter = moviesAdapter
        viewModelFragmentVM.moviesListLiveData.observe(viewLifecycleOwner) {
            if (it.isEmpty()) showToast(R.string.bad_connection)
            moviesAdapter.refresh(it.map { movieEntity ->  movieEntity.toMovie() })
        }
    }

    private fun showToast(resId: Int) {
        Toast.makeText(requireContext(),getText(resId),Toast.LENGTH_SHORT).show()
    }

}
