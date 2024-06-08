package com.victor.valemovie

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.victor.valemovie.adapter.MovieAdapter
import com.victor.valemovie.api.RetrofitService
import com.victor.valemovie.databinding.ActivityMainBinding
import com.victor.valemovie.model.MovieResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val movieAPI by lazy {
        RetrofitService.movieAPI
    }

    private lateinit var movieAdapter: MovieAdapter
    var jobMoviePopular: Job? = null
    var jobMovieRecent: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeView()

    }

    override fun onStart() {
        super.onStart()
        //recoveredRecentMovie()
        recoveredMoviePopular()
    }

    private fun recoveredMoviePopular() {
        jobMoviePopular = CoroutineScope(Dispatchers.IO).launch {
            var response: Response<MovieResponse>? = null

            try {
                response = movieAPI.recoveredPopularMovie(1)
            }catch (e: Exception){
                showMessenge("Error returning data")
            }

            if (response != null){
                if(response.isSuccessful){

                    val movieResponse = response.body()
                    val listMovies = movieResponse?.movies
                    if(listMovies != null && listMovies.isNotEmpty()){

                        withContext(Dispatchers.Main){

                            movieAdapter.addList(listMovies)

                        }

                    }

                }else{
                    showMessenge("Error returning data, code:${response.code()}")
                }
            }else{
                showMessenge("Error returning data")
            }

        }
    }

    /*private fun recoveredRecentMovie() {

        jobMovieRecent = CoroutineScope(Dispatchers.IO).launch{

            try {

            }catch (e: Exception){
                showMessenge("Error")
            }


        }

    }*/

    private fun initializeView() {

        movieAdapter = MovieAdapter()
        binding.rvLista.adapter = movieAdapter

        binding.rvLista.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )


    }

    private fun showMessenge(messenge: String){
        Toast.makeText(
            applicationContext,
            messenge,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onStop() {
        super.onStop()
        jobMovieRecent?.cancel()
    }






}