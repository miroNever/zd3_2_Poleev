package com.example.tvapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.tvapplication.databinding.FragmentMoviesByGenreBinding
import org.json.JSONObject
import java.lang.Exception

class MoviesByGenreFragment : Fragment() {

    private lateinit var binding: FragmentMoviesByGenreBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var requestQueue: RequestQueue

    companion object {
        private const val ARG_KEY = "myKey"

        fun newInstance(yourValue: String): MoviesByGenreFragment {
            val fragment = MoviesByGenreFragment()
            val args = Bundle()
            args.putString(ARG_KEY, yourValue)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesByGenreBinding.inflate(inflater, container, false)
        binding.recyclerViewMovies.layoutManager = GridLayoutManager(requireContext(), 3)
        movieAdapter = MovieAdapter(requireContext(), emptyList())
        binding.recyclerViewMovies.adapter = movieAdapter

        movieAdapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(movie: Movie) {
                val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
                intent.putExtra("movieTitle", movie.title)
                startActivity(intent)
            }
        })

        val genre = arguments?.getString(ARG_KEY)
        requestQueue = Volley.newRequestQueue(requireContext())

        if (genre != null) {
            fetchMoviesByGenre(genre)
        }
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Метод, вызываемый перед изменением текста
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Метод, вызываемый при изменении текста
                movieAdapter.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable?) {
                // Метод, вызываемый после изменения текста
            }
        })
        return binding.root
    }

    private fun fetchMoviesByGenre(genre: String) {
        val url = "https://www.omdbapi.com/?apikey=bc17ab59&s=${genre}&type=movie"

        val request = StringRequest(Request.Method.GET, url,
            { response ->
                try {
                    Log.d("Movie", "Raw JSON Response: $response")

                    val jsonObject = JSONObject(response)
                    val error = jsonObject.optString("Error")

                    if (error.isNullOrEmpty()) {
                        val searchArray = jsonObject.optJSONArray("Search")

                        // Проверка на null итерируемого объекта
                        if (searchArray != null) {
                            val movies = mutableListOf<Movie>()

                            // Вывод списка фильмов
                            for (i in 0 until searchArray.length()) {
                                val movieObject = searchArray.optJSONObject(i)
                                val title = movieObject.optString("Title")
                                val description = movieObject.optString("Plot")
                                val posterUrl = movieObject.optString("Poster")

                                movies.add(Movie(title, description, posterUrl))
                            }

                            if (movies.isNotEmpty()) {
                                movies.forEach {
                                    Log.d(
                                        "Movie",
                                        "${it.title}\n${it.description}\n${it.posterUrl}\n"
                                    )
                                }
                                movieAdapter.setMovies(movies)
                            } else {
                                Log.d("Movie", "No movies found.")
                            }
                        } else {
                            Log.e("Volley", "Search results are null")
                        }
                    } else {
                        Log.e("Volley", "Error: $error")
                    }
                } catch (e: Exception) {
                    Log.e("Volley", "Error parsing JSON: ${e.message}")
                }
            },
            { error ->
                Log.e("Volley", "Error: ${error.message}")
            })

        requestQueue.add(request)
    }
}
