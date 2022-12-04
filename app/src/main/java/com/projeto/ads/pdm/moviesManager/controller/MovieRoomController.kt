package com.projeto.ads.pdm.moviesManager.controller

import android.os.AsyncTask
import androidx.room.Room
import com.projeto.ads.pdm.moviesManager.model.dao.MovieRoomDAO
import com.projeto.ads.pdm.moviesManager.model.database.MovieRoomDatabase
import com.projeto.ads.pdm.moviesManager.model.entity.Movie
import com.projeto.ads.pdm.moviesManager.view.MainActivity

class MovieRoomController(private val mainActivity: MainActivity) {

    private val movieDAOImpl: MovieRoomDAO by lazy {
        Room.databaseBuilder(mainActivity, MovieRoomDatabase::class.java, MovieRoomDAO.MOVIE_DATABASE_FILE).build().getMovieRoomDao()
    }

    fun insert(movie: Movie) {
        Thread {
            movieDAOImpl.create(movie)
            getAll()
        }.start()
    }

    fun listOne(name: String) = movieDAOImpl.getOne(name)

    fun getAll() {
        object: AsyncTask<Unit, Unit, MutableList<Movie>>(){
            override fun doInBackground(vararg params: Unit?): MutableList<Movie> {
                val returnList = mutableListOf<Movie>()
                returnList.addAll(movieDAOImpl.getAll())
                return returnList
            }

            override fun onPostExecute(result: MutableList<Movie>?) {
                super.onPostExecute(result)
                if (result != null){
                    mainActivity.updateMovieList(result)
                }
            }
        }.execute()
    }

    fun update(movie: Movie) {
        Thread {
            movieDAOImpl.update(movie)
            getAll()
        }.start()
    }

    fun delete(movie: Movie) {
        Thread {
            movieDAOImpl.delete(movie)
            getAll()
        }.start()
    }
}