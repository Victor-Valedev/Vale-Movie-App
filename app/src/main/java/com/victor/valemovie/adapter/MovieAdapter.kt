package com.victor.valemovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.victor.valemovie.api.RetrofitService
import com.victor.valemovie.databinding.ItemFilmeBinding
import com.victor.valemovie.model.Movie

class MovieAdapter(
    val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovie = mutableListOf<Movie>()

    fun addList(list: List<Movie>){
        this.listMovie.addAll(list)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(val binding: ItemFilmeBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(movie: Movie){

            val nameMovie = movie.backdrop_path
            val sizeMovie = "w780"
            val urlImageBase = RetrofitService.IMAGE_URL

            val urlMovie = urlImageBase + sizeMovie + nameMovie

            Picasso.get()
                .load(urlMovie)
                .into(binding.imageItemFilme)

            binding.textTitulo.text = movie.title
            binding.clItem.setOnClickListener {
                onClick(movie)
            }
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