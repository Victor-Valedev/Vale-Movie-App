package com.victor.valemovie

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.victor.valemovie.adapter.MovieAdapter
import com.victor.valemovie.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
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
        recoveredRecentMovie()
    }

    private fun recoveredRecentMovie() {

        jobMovieRecent = CoroutineScope(Dispatchers.IO).launch{

            try {

            }catch (e: Exception){
                showMessenge("Error")
            }


        }

    }

    private fun initializeView() {
        TODO("Not yet implemented")
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