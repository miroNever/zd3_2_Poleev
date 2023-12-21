package com.example.tvapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MovieAdapter(private val context: Context, private var moviesList: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(), Filterable {
    private var filteredMoviesList: List<Movie> = moviesList.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = filteredMoviesList[position]
        holder.bind(movie)
    }


    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val queryString = constraint?.toString()?.toLowerCase()
                val filterResults = FilterResults()
                filterResults.values = if (queryString.isNullOrEmpty()) {
                    moviesList.toList()
                } else {
                    moviesList.filter {
                        it.title.toLowerCase().contains(queryString)
                    }
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredMoviesList = results?.values as List<Movie>
                notifyDataSetChanged()
            }
        }
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = filteredMoviesList.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewPoster: ImageView = itemView.findViewById(R.id.imageViewPoster)
        private val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        private val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedMovie = moviesList[position]
                    onItemClickListener?.onItemClick(clickedMovie)
                }
            }
        }

        fun bind(movie: Movie) {
            // Установка значений элементов пользовательского интерфейса
            textViewTitle.text = movie.title
            textViewDescription.text = movie.description

            // Использование библиотеки Picasso для загрузки изображения
            Picasso.get()
                .load(movie.posterUrl)
                .placeholder(R.drawable.placeholder_poster)
                .error(R.drawable.placeholder_poster)
                .into(imageViewPoster)
        }
    }

    fun setMovies(newMoviesList: List<Movie>) {
        moviesList = newMoviesList
        filteredMoviesList = newMoviesList
        notifyDataSetChanged()
    }

}
