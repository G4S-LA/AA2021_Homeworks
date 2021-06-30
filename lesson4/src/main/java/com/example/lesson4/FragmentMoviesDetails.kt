package com.example.lesson4

import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesDetails(private var movie: Movie) : Fragment(R.layout.fragment_movies_details) {
    private val tvAge: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_back) }
    private val tvFilm: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_film) }
    private val tvGenre: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_genre) }
    private val rating: RatingBar by lazy { requireView().findViewById<RatingBar>(R.id.rating) }
    private val tvNumReviews: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_num_reviews) }
    private val tvStoryLne: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_storyline_text) }
    private val actorsAdapter: ActorsAdapter by lazy {
        ActorsAdapter(requireContext()).apply {
            setList(generateActors())
        }
    }
    private val rvActors: RecyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.rv_actors).apply {

            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }
    private val tvBack: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_back) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        rvActors.adapter = actorsAdapter
    }
}

