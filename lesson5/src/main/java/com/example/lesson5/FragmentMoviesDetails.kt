package com.example.lesson5

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.lesson5.model.Movie

class FragmentMoviesDetails(private val movie: Movie) : Fragment(R.layout.fragment_movies_details) {
    private val tvAge: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_age) }
    private val tvFilm: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_film) }
    private val tvGenre: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_genre) }
    private val rating: RatingBar by lazy { requireView().findViewById<RatingBar>(R.id.rating) }
    private val tvNumReviews: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_num_reviews) }
    private val tvStoryLne: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_storyline_text) }
    private val tvBack: TextView by lazy { requireView().findViewById<TextView>(R.id.tv_back) }
    private val image: ImageView by lazy { requireView().findViewById<ImageView>(R.id.iv_movie_details_image) }
    private val actorsAdapter: ActorsAdapter by lazy { ActorsAdapter(requireContext()) }
    private val rvActors: RecyclerView by lazy {
        requireView().findViewById<RecyclerView>(R.id.rv_actors).apply {

            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvAge.text = movie.pgAge.toString().plus("+")
        tvFilm.text = movie.title
        tvGenre.text = movie.genres.joinToString(separator = ", ") { it.name }
        rating.rating = movie.rating.toFloat() / 2
        tvNumReviews.text = movie.reviewCount.toString().plus(" REVIEWS")
        tvStoryLne.text = movie.storyLine

        val requestOptions = RequestOptions().apply {
            transform(CenterCrop(), RoundedCorners(16))
        }
        Glide.with(requireContext())
            .load(movie.detailImageUrl)
            .apply(requestOptions)
            .into(image)
        actorsAdapter.setList(movie.actors)

        tvBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        rvActors.adapter = actorsAdapter
    }
}

