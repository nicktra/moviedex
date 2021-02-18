package com.nicktra.moviedex.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
        var id: Int,
        var title: String,
        var overview: String,
        var posterPath: String,
        var backdropPath: String,
        var releaseDate: String,
        var voteAverage: Double,
        var popularity: Double,
        var isFavorite: Boolean,
        var isTvShow: Boolean
) : Parcelable