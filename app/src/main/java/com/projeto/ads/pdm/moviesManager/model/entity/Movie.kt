package com.projeto.ads.pdm.moviesManager.model.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Movie(
    @PrimaryKey
    var name: String,
    @NonNull
    var year : Int,
    @NonNull
    var studio: String,
    @NonNull
    var duration : Int,
    @NonNull
    var watched : Boolean,
    var grade : Int,
    @NonNull
    var genre : String,
) : Parcelable