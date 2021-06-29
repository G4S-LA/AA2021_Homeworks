package com.example.lesson4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesDetails(private val movie: Movie) : Fragment() {
    private lateinit var tvAge: TextView
    private lateinit var tvFilm: TextView
    private lateinit var tvGenre: TextView
    private lateinit var rating: RatingBar
    private lateinit var tvNumReviews: TextView
    private lateinit var tvStoryLne: TextView
    private lateinit var rvActors: RecyclerView
    private lateinit var tvBack: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_movies_details, container, false)

        init(rootView)
//        setValues()
        return rootView
    }

    private fun init(rootView: View) {
        tvAge = rootView.findViewById(R.id.tv_age)
        tvFilm = rootView.findViewById(R.id.tv_film)
        tvGenre = rootView.findViewById(R.id.tv_genre)
        rating = rootView.findViewById(R.id.rating)
        tvNumReviews = rootView.findViewById(R.id.tv_num_reviews)
        tvStoryLne = rootView.findViewById(R.id.tv_storyline_text)
        tvBack = rootView.findViewById(R.id.tv_back)
        tvBack.setOnClickListener {
            activity?.onBackPressed()
        }
        rvActors = rootView.findViewById(R.id.rv_actors)
        rvActors.adapter = context?.let { ActorsAdapter(it, generateActors()) }
        rvActors.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

/*    private fun setValues() {
        tvAge.text = movie.age
        tvFilm.text = movie.name
        tvGenre.text = movie.genres
        tvStoryLne.text = movie.storyLine
        rating.rating = movie.rating
        tvNumReviews.text = "${movie.numReviews} REVIEWS"
    }*/

}

fun generateActors(): List<Actor> {

    val ironMan = Actor(
        "Robert Downey Jr.",
        R.drawable.iron_man
    )

    val captainAmerica = Actor(
        "Chris Evans",
        R.drawable.captain_a
    )

    val hulk = Actor(
        "Mark Ruffalo",
        R.drawable.hulk
    )

    val thor = Actor(
        "Chris Hemsworth",
        R.drawable.thor
    )



    return listOf(ironMan, captainAmerica, hulk, thor)
}