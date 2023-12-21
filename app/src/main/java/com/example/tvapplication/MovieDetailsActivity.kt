package com.example.tvapplication

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.tvapplication.databinding.ActivityMovieDetailsBinding
import com.squareup.picasso.Picasso

class MovieDetailsActivity : Activity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieTitle = intent.getStringExtra("movieTitle")
        requestQueue = Volley.newRequestQueue(this)
        if (!movieTitle.isNullOrEmpty()) {
            getMovieDetails(movieTitle)
        }
    }

    private fun getMovieDetails(title: String) {
        val apiKey = "bc17ab59"
        val url = "https://www.omdbapi.com/?apikey=$apiKey&t=$title"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    Picasso.get()
                        .load(response.optString("Poster"))
                        .placeholder(R.drawable.placeholder_poster)
                        .error(R.drawable.placeholder_poster)
                        .into(binding.imageViewPosterDetails)
                    binding.textViewTitle.text = title
                    binding.textViewDescription.text = response.optString("Plot")
                    binding.textViewRating.text = "Оценка на IMDB ${response.optString("imdbRating")}"
                    Log.d("Volley",response.toString())

                } catch (e: Exception) {
                    Log.e("Volley", "Error parsing response: $e")
                }
            },
            { error ->
                Log.e("Volley", "Error: ${error.message}")
            }
        )

        requestQueue.add(request)
    }
}