package com.projeto.ads.pdm.moviesManager.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.projeto.ads.pdm.moviesManager.R
import com.projeto.ads.pdm.moviesManager.controller.MovieRoomController
import com.projeto.ads.pdm.moviesManager.databinding.ActivityMainBinding
import com.projeto.ads.pdm.moviesManager.model.Constant.EXTRA_MOVIE
import com.projeto.ads.pdm.moviesManager.model.entity.Movie

class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var movieActivityResultLauncher: ActivityResultLauncher<Intent>

    private val movieList: MutableList<Movie> = mutableListOf()

    private var adding : Boolean = false

    private val movieController: MovieRoomController by lazy {
        MovieRoomController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        movieActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == RESULT_OK) {
                val movie = result.data?.getParcelableExtra<Movie>(EXTRA_MOVIE)
                movie?.let { _movie->
                    val position = movieList.indexOfFirst { it.name == movie.name }
                    if (position != -1) {
                        if(!adding) {
                            movieController.update(_movie)
                        }
                        else {
                            Toast.makeText(this, "Não é possível cadastrar mais de um filme com o mesmo nome.", Toast.LENGTH_LONG).show()
                        }
                    }
                    else {
                        movieController.insert(_movie)
                    }
                    //Adapter.notifyDataSetChanged() Implementação Futura
                }
            }
            else Toast.makeText(this, "Operação cancelada.", Toast.LENGTH_SHORT).show()
            adding = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addMovieMi -> {
                movieActivityResultLauncher.launch(Intent(this, MovieActivity::class.java))
                adding = true
                true
            }
            else -> { false }
        }
    }

    fun updateMovieList(_movieList: MutableList<Movie>) {
        movieList.clear()
        movieList.addAll(_movieList)
        //Adapter.notifyDataSetChanged() Implementação Futura
    }
}