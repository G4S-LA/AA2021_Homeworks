package com.example.lesson4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActorsAdapter(context: Context,
                    var actors: List<Actor>
) : RecyclerView.Adapter<ActorsAdapter.ViewHolderActor>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private fun getItem(position: Int): Actor = actors[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderActor {
        return ViewHolderActor(inflater.inflate(R.layout.view_holder_actor,parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderActor, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = actors.size

    inner class ViewHolderActor(v : View) : RecyclerView.ViewHolder(v) {
        private val image: ImageView = itemView.findViewById(R.id.iv_actor)
        private val genres: TextView = itemView.findViewById(R.id.tv_actor_name)

        fun bind(actor: Actor) {
            genres.text = actor.name
            image.setImageResource(actor.image)
        }
    }
}