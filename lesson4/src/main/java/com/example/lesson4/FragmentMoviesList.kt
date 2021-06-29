package com.example.lesson4

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesList : Fragment(), MoviesAdapter.OnMovieListener {
    private lateinit var rvMovies: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_movies_list, container, false)

        rvMovies = rootView.findViewById(R.id.rv_movies)
        rvMovies.adapter = context?.let { MoviesAdapter(it, generateMovies(it), this) }
        rvMovies.addItemDecoration(SpacesItemDecoration(20))
        rvMovies.layoutManager = GridLayoutManager(context, 2)

        return rootView
    }

    override fun onClickMovie(movie: Movie) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.main, FragmentMoviesDetails(movie))
            ?.addToBackStack(null)?.commit()
    }

}

fun generateMovies(context: Context): List<Movie> {

    val movie = Movie(
        context.getString(R.string.film_name), context.getString(R.string.age),context.getString(R.string.storyline_text), 4f,
        196, context.getString(R.string.genres),140, true, R.drawable.movie_1_list
    )

    val movie2 = Movie(
        context.getString(R.string.film_name), context.getString(R.string.age),context.getString(R.string.storyline_text), 4f,
        196, context.getString(R.string.genres), 140, true, R.drawable.movie_1_list
    )

    val movie3 = Movie(
        context.getString(R.string.film_name), context.getString(R.string.age),context.getString(R.string.storyline_text), 4f,
        196, context.getString(R.string.genres), 140, true, R.drawable.movie_1_list
    )

    val movie4 = Movie(
        context.getString(R.string.film_name), context.getString(R.string.age),context.getString(R.string.storyline_text), 4f,
        196, context.getString(R.string.genres), 140, true, R.drawable.movie_1_list
    )

    return listOf(movie, movie2, movie3, movie4)
}
