package com.example.tmdb.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.responses.TmdbMovie
import kotlinx.android.synthetic.main.item_movie.view.txt_avg_vote
import kotlinx.android.synthetic.main.item_movie.view.txt_description
import kotlinx.android.synthetic.main.item_movie.view.txt_name

class MovieAdapter(private val data: List<TmdbMovie>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).bind(data[position])
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is MovieViewHolder) {
            holder.unbind()
        } else {
            super.onViewRecycled(holder)
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieName = itemView.txt_name
        private val movieDesc = itemView.txt_description
        private val movieAvgVote = itemView.txt_avg_vote

        fun bind(movie: TmdbMovie) {
            movieName.text = movie.title
            movieDesc.text = movie.overview
            movieAvgVote.text = movie.vote_average.toString()
        }

        fun unbind() {
            movieName.text = ""
            movieDesc.text = ""
            movieAvgVote.text = ""
        }
    }
}