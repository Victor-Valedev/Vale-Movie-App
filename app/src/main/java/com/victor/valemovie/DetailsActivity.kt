package com.victor.valemovie

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import com.victor.valemovie.api.RetrofitService
import com.victor.valemovie.databinding.ActivityDetailsBinding
import com.victor.valemovie.model.Movie

class DetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras
        if(bundle != null){

            val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("movie", Movie::class.java)
            } else {
                bundle.getParcelable("movie") as? Movie
            }

            if(movie != null){
                binding.textFilmeTitulo.text = movie.title
                binding.textResultSinapse.text = movie.overview

                val formattedRating = String.format("%.1f", movie.vote_average)
                binding.textReview.text = formattedRating

                val nameMovie = movie.backdrop_path
                val sizeMovie = "w780"
                val urlBase = RetrofitService.IMAGE_URL

                val urlMovie = urlBase + sizeMovie + nameMovie

                Picasso.get()
                    .load(urlMovie)
                    .into(binding.imgPoster)
            }



        }

    }
}