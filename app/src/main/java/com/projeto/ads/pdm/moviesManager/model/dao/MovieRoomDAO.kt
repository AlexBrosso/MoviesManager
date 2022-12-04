package com.projeto.ads.pdm.moviesManager.model.dao

import androidx.room.*
import com.projeto.ads.pdm.moviesManager.model.entity.Movie

@Dao
interface MovieRoomDAO {

    companion object Constant {
        const val MOVIE_DATABASE_FILE = "movie_room"
        const val MOVIE_TABLE = "movie"
        const val NAME_COLUMN = "name"
    }

    @Insert
    fun create(movie: Movie)

    @Query("SELECT * FROM $MOVIE_TABLE WHERE $NAME_COLUMN = :name")
    fun getOne(name: String): Movie?

    @Query("SELECT * FROM $MOVIE_TABLE")
    fun getAll(): MutableList<Movie>

    @Update
    fun update(movie: Movie): Int

    @Delete
    fun delete(movie: Movie): Int
}