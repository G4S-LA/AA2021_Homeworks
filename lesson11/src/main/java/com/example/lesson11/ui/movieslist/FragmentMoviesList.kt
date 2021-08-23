package com.example.lesson11.ui.movieslist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson11.R
import com.example.lesson11.adapters.MoviesAdapter
import com.example.lesson11.adapters.SpacesItemDecoration
import com.example.lesson11.model.Movie
import com.example.lesson11.ui.moviesdetails.FragmentMoviesDetails
import com.example.lesson11.viewmodels.FragmentMoviesListVM
import com.google.android.material.transition.MaterialElevationScale

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {
    private val viewModelFragmentVM: FragmentMoviesListVM by lazy {
        ViewModelProvider(this).get(FragmentMoviesListVM::class.java)
    }

    companion object {
        const val MOVIE_ID = "movie_id"
    }

    private val moviesAdapter: MoviesAdapter by lazy { MoviesAdapter(requireContext(), movieDetails) }

    private val rvMovies: RecyclerView
    get() = requireView().findViewById<RecyclerView>(R.id.rv_movies).apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(SpacesItemDecoration(20))
        }

    private val movieDetails = object : MoviesAdapter.OnMovieListener {
        override fun onClickMovie(v: View, movie: Movie) {

            exitTransition = MaterialElevationScale(false).apply {
                duration = 500L
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = 500L
            }
            val fragment = FragmentMoviesDetails()

            fragment.arguments = Bundle().apply { putInt(MOVIE_ID, movie.id) }
            requireActivity().supportFragmentManager.beginTransaction()
                    .addSharedElement(v, requireContext().getString(R.string.movie_detail_transition_name))
                    .replace(R.id.main_container, fragment)
                    .addToBackStack("123")
                    .commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("§", "onCreateView: $this")

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        rvMovies.adapter = moviesAdapter
        viewModelFragmentVM.moviesListLiveData.observe(viewLifecycleOwner) {
            if (it.isEmpty()) showToast(R.string.bad_connection)
            moviesAdapter.refresh(it.map { movieEntity -> movieEntity.toMovie() })
            Log.v("§", it.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("§", "destroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("§", "destroy")
    }

    private fun showToast(resId: Int) {
        Toast.makeText(requireContext(), getText(resId), Toast.LENGTH_SHORT).show()
    }

}
