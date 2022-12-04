package com.projeto.ads.pdm.moviesManager.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.projeto.ads.pdm.moviesManager.model.dao.MovieRoomDAO
import com.projeto.ads.pdm.moviesManager.model.entity.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieRoomDatabase: RoomDatabase() {
        abstract fun getMovieRoomDao(): MovieRoomDAO
}