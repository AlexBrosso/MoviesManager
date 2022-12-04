package com.projeto.ads.pdm.moviesManager.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.projeto.ads.pdm.moviesManager.databinding.ActivityMovieBinding
import com.projeto.ads.pdm.moviesManager.model.Constant.EXTRA_MOVIE
import com.projeto.ads.pdm.moviesManager.model.Constant.VIEW_MOVIE
import com.projeto.ads.pdm.moviesManager.model.entity.Movie

class MovieActivity : AppCompatActivity()  {

    private val activityMovieBinding: ActivityMovieBinding by lazy {
        ActivityMovieBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMovieBinding.root)

        activityMovieBinding.gradeEt.isEnabled = false

        val receivedPerson =  intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        receivedPerson?.let{ _receivedPerson ->
            activityMovieBinding.titleTv.text = "Editar Filme"
            activityMovieBinding.nameEt.isEnabled = false
            with(activityMovieBinding)
            {
                with(_receivedPerson)
                {
                    nameEt.setText(name)
                    yearEt.setText(year.toString())
                    studioEt.setText(studio)
                    durationEt.setText(duration.toString())
                    gradeEt.setText(if(grade == -1) "" else grade.toString())
                    watchedCb.isChecked = watched
                    genreEt.setText(genre)

                    if(watched) gradeEt.isEnabled = true;
                }
            }
        }

        if (intent.getBooleanExtra(VIEW_MOVIE, false)) {
            activityMovieBinding.titleTv.text = "Visualizar Filme"
            activityMovieBinding.nameEt.isEnabled = false
            activityMovieBinding.yearEt.isEnabled = false
            activityMovieBinding.studioEt.isEnabled = false
            activityMovieBinding.durationEt.isEnabled = false
            activityMovieBinding.gradeEt.isEnabled = false
            activityMovieBinding.watchedCb.isEnabled = false
            activityMovieBinding.genreEt.isEnabled = false
            activityMovieBinding.saveBt.visibility = View.GONE
        }

        activityMovieBinding.saveBt.setOnClickListener {
            val movieName = activityMovieBinding.nameEt.text.toString()
            val movieYear = activityMovieBinding.yearEt.text.toString()
            val movieStudio = activityMovieBinding.studioEt.text.toString()
            val movieDuration = activityMovieBinding.durationEt.text.toString()
            val movieGrade = if(activityMovieBinding.gradeEt.text.isNotEmpty()) activityMovieBinding.gradeEt.text.toString().toInt() else -1
            val movieWatched = activityMovieBinding.watchedCb.isChecked
            val movieGenre = activityMovieBinding.genreEt.text.toString()

            if (!movieName.isNullOrEmpty() && !movieYear.isNullOrEmpty() && !movieYear.isNullOrEmpty() && !movieDuration.isNullOrEmpty() && !movieGenre.isNullOrEmpty())
            {
                val movie = Movie(
                    name = movieName,
                    year = movieYear.toInt(),
                    studio = movieStudio,
                    duration = movieDuration.toInt(),
                    grade = movieGrade,
                    watched = movieWatched,
                    genre = movieGenre
                )
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_MOVIE, movie)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
            else Toast.makeText(this, "Existem campos preenchidos incorretamente.", Toast.LENGTH_SHORT).show()
        }

        activityMovieBinding.cancelBt.setOnClickListener{
            setResult(RESULT_CANCELED)
            finish()
        }

        activityMovieBinding.watchedCb.setOnCheckedChangeListener { _, isChecked ->
            if(!isChecked){
                activityMovieBinding.gradeEt.text = null
            }

            activityMovieBinding.gradeEt.isEnabled = isChecked
        }
    }
}