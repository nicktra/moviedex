package com.nicktra.moviedex.core.data.source.remote

import android.util.Log
import com.nicktra.moviedex.BuildConfig
import com.nicktra.moviedex.core.data.source.remote.network.ApiResponse
import com.nicktra.moviedex.core.data.source.remote.network.ApiService
import com.nicktra.moviedex.core.data.source.remote.response.MovieResponse
import com.nicktra.moviedex.core.data.source.remote.response.ShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        const val API_KEY = BuildConfig.API_KEY_TMDB
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieList(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllShows(): Flow<ApiResponse<List<ShowResponse>>> {
        return flow {
            try {
                val response = apiService.getShowList(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}