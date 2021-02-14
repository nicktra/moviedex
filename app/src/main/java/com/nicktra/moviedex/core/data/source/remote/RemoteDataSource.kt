package com.nicktra.moviedex.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nicktra.moviedex.BuildConfig
import com.nicktra.moviedex.core.data.source.remote.network.ApiResponse
import com.nicktra.moviedex.core.data.source.remote.network.ApiService
import com.nicktra.moviedex.core.data.source.remote.response.ListMovieResponse
import com.nicktra.moviedex.core.data.source.remote.response.ListShowResponse
import com.nicktra.moviedex.core.data.source.remote.response.MovieResponse
import com.nicktra.moviedex.core.data.source.remote.response.ShowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun getAllMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        //get data from remote api
        val client = apiService.getMovieList(API_KEY)

        client.enqueue(object : Callback<ListMovieResponse> {
            override fun onResponse(
                call: Call<ListMovieResponse>,
                response: Response<ListMovieResponse>
            ) {
                val dataArray = response.body()?.results
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }

    fun getAllShows(): LiveData<ApiResponse<List<ShowResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<ShowResponse>>>()

        //get data from remote api
        val client = apiService.getShowList(API_KEY)

        client.enqueue(object : Callback<ListShowResponse> {
            override fun onResponse(
                call: Call<ListShowResponse>,
                response: Response<ListShowResponse>
            ) {
                val dataArray = response.body()?.results
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<ListShowResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }
}