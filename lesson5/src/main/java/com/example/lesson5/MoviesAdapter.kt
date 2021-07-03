package com.example.lesson5

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.lesson5.model.Movie
import kotlinx.coroutines.Deferred

class MoviesAdapter(
    private val context: Context,
    private var movies: List<Movie>,
    private val listener: OnMovieListener
) : RecyclerView.Adapter<MoviesAdapter.ViewHolderMovie>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private fun getItem(position: Int): Movie = movies[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {
        return ViewHolderMovie(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {
        holder.bind(getItem(position))
    }

    fun refresh(moviesList: List<Movie>) {
        movies = moviesList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolderMovie(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private val image: ImageView = itemView.findViewById(R.id.iv_movie_1_list)
        private val genres: TextView = itemView.findViewById(R.id.tv_genre)
        private val name: TextView = itemView.findViewById(R.id.tv_film_name)
        private val age: TextView = itemView.findViewById(R.id.tv_age)
        private val duration: TextView = itemView.findViewById(R.id.tv_duration)
        private val numReviews: TextView = itemView.findViewById(R.id.tv_num_reviews)
        private val rating: RatingBar = itemView.findViewById(R.id.rating)
        private val favorite: ImageView = itemView.findViewById(R.id.iv_like)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            genres.text = movie.genres.joinToString(separator = ", ") { it.name }
            name.text = movie.title
            age.text = movie.pgAge.toString().plus("+")
            duration.text = movie.runningTime.toString().plus(" MIN")
            numReviews.text = movie.reviewCount.toString().plus(" REVIEWS")
            rating.rating = movie.rating.toFloat() / 2
            favorite.setColorFilter(
                if (movie.isLiked) {
                    ContextCompat.getColor(context, R.color.pink)
                } else {
                    ContextCompat.getColor(context, R.color.white)
                }
            )
            val requestOptions = RequestOptions().apply {
                transform(CenterCrop(), RoundedCorners(16))
            }
            Glide.with(context)
                .load(movie.imageUrl)
                .apply(requestOptions)
                .into(image)
        }

        override fun onClick(v: View?) {
            val pos = adapterPosition

            if (pos != RecyclerView.NO_POSITION) {
                listener.onClickMovie(getItem(pos))
            }
        }
    }

    interface OnMovieListener {
        fun onClickMovie(movie: Movie)
    }
}