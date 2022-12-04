package com.projeto.ads.pdm.moviesManager.model.dao

import com.projeto.ads.pdm.moviesManager.model.entity.Movie

interface MovieDAO {
    fun create(movie: Movie): Int
    fun getOne(name: String): Movie?
    fun getAll(): MutableList<Movie>
    fun update(movie: Movie): Int
    fun delete(name: String): Int
}