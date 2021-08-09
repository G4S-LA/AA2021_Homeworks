package com.example.lesson9.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.lesson9.R
import com.example.lesson9.model.Actor

class ActorsAdapter(private val context: Context) : RecyclerView.Adapter<ActorsAdapter.ViewHolderActor>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var actors: List<Actor> = listOf()

    private fun getItem(position: Int): Actor = actors[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderActor {
        return ViewHolderActor(inflater.inflate(R.layout.item_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderActor, position: Int) {
        holder.bind(getItem(position))
    }

    fun setList(newActors: List<Actor>) {
        this.actors = newActors
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = actors.size

    inner class ViewHolderActor(v: View) : RecyclerView.ViewHolder(v) {
        private val image: ImageView = itemView.findViewById(R.id.iv_actor)
        private val genres: TextView = itemView.findViewById(R.id.tv_actor_name)

        fun bind(actor: Actor) {
            genres.text = actor.name
            val requestOptions = RequestOptions().apply{
                transform(CenterCrop(),RoundedCorners(20))
            }
            Glide.with(image.context)
                .load(actor.imageUrl)
                .apply(requestOptions)
                .into(image)
        }
    }
}