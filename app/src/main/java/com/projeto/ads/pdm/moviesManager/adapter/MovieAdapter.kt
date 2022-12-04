package com.projeto.ads.pdm.moviesManager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.projeto.ads.pdm.moviesManager.R
import com.projeto.ads.pdm.moviesManager.model.entity.Movie

class MovieAdapter(context : Context, private val movieList: MutableList<Movie>) : ArrayAdapter<Movie>(context, R.layout.tile_movie, movieList) {

    private data class TileMovieHolder(val nameTv: TextView, val genreAndYearTv: TextView, val gradeTv: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val movie = movieList[position]
        var movieTileView = convertView
        if (movieTileView == null) {

            movieTileView = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.tile_movie, parent, false)

            val tileMovieHolder = TileMovieHolder(
                movieTileView.findViewById(R.id.nameTv),
                movieTileView.findViewById(R.id.genreAndYearTv),
                movieTileView.findViewById(R.id.gradeTv),
            )
            movieTileView.tag = tileMovieHolder
        }

        with(movieTileView?.tag as TileMovieHolder) {
            nameTv.text = movie.name
            genreAndYearTv.text = movie.genre + " - " + movie.year.toString()
            gradeTv.text = if(movie.grade != -1) " Nota: " + movie.grade.toString() else " Nota: Indefinida"
        }

        return movieTileView
    }
}