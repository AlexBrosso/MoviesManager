package com.projeto.ads.pdm.moviesManager.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.projeto.ads.pdm.moviesManager.R
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

        activityMovieBinding.gradeSp.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.grades))
        activityMovieBinding.genreSp.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.genres))

        activityMovieBinding.gradeSp.setSelection(11)
        activityMovieBinding.gradeSp.isEnabled = false

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
                    gradeSp.setSelection(grade)
                    watchedCb.isChecked = watched
                    genreSp.setSelection(resources.getStringArray(R.array.genres).indexOf(genre))

                    if(watched) gradeSp.isEnabled = true;
                }
            }
        }

        if (intent.getBooleanExtra(VIEW_MOVIE, false)) {
            activityMovieBinding.titleTv.text = "Visualizar Filme"
            activityMovieBinding.nameEt.isEnabled = false
            activityMovieBinding.yearEt.isEnabled = false
            activityMovieBinding.studioEt.isEnabled = false
            activityMovieBinding.durationEt.isEnabled = false
            activityMovieBinding.gradeSp.isEnabled = false
            activityMovieBinding.watchedCb.isEnabled = false
            activityMovieBinding.genreSp.isEnabled = false
            activityMovieBinding.saveBt.visibility = View.GONE
        }

        activityMovieBinding.saveBt.setOnClickListener {
            val movieName = activityMovieBinding.nameEt.text.toString()
            val movieYear = activityMovieBinding.yearEt.text.toString()
            val movieStudio = activityMovieBinding.studioEt.text.toString()
            val movieDuration = activityMovieBinding.durationEt.text.toString()
            val movieGrade = activityMovieBinding.gradeSp.selectedItemPosition;
            val movieWatched = activityMovieBinding.watchedCb.isChecked
            val movieGenre = activityMovieBinding.genreSp.selectedItem.toString()

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
                activityMovieBinding.gradeSp.setSelection(11)
            }

            activityMovieBinding.gradeSp.isEnabled = isChecked
        }
    }
}