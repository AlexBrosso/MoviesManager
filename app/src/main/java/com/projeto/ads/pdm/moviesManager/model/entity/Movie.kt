package com.projeto.ads.pdm.moviesManager.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var name: String,
    var year : Int,
    var duration : Int,
    var watched : Boolean,
    var grade : Int,
    var genre : String,
) : Parcelable