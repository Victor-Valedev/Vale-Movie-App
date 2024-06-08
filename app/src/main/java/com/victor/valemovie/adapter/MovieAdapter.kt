package com.victor.valemovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.victor.valemovie.databinding.ItemFilmeBinding
import com.victor.valemovie.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovie: MutableList<String> = mutableListOf()

    fun addList(list: List<Movie>){

    }

    inner class MovieViewHolder(binding: ItemFilmeBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(movie: Movie){

            val nameMovie = movie.backdrop_path


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmeBinding.inflate(
            layoutInflater,
            parent,
            false
        )

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        var movie = listMovie[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

}