package com.nicktra.moviedex.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Show(
        var showId: Int,
        var name: String,
        var overview: String,
        var posterPath: String,
        var backdropPath: String,
        var firstAirDate: String,
        var voteAverage: Double,
        var popularity: Double,
        var isFavorite: Boolean
) : Parcelable
