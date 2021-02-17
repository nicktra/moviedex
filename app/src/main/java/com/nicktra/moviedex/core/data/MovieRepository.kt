package com.nicktra.moviedex.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.nicktra.moviedex.core.data.source.local.LocalDataSource
import com.nicktra.moviedex.core.data.source.remote.RemoteDataSource
import com.nicktra.moviedex.core.data.source.remote.network.ApiResponse
import com.nicktra.moviedex.core.data.source.remote.response.MovieResponse
import com.nicktra.moviedex.core.data.source.remote.response.ShowResponse
import com.nicktra.moviedex.core.domain.model.Movie
import com.nicktra.moviedex.core.domain.repository.IMovieRepository
import com.nicktra.moviedex.core.utils.AppExecutors
import com.nicktra.moviedex.core.utils.DataMapper

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllMovies(): LiveData<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> {
                return Transformations.map(localDataSource.getAllMovies()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesMovieToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()

    override fun getAllShows(): LiveData<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<ShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> {
                return Transformations.map(localDataSource.getAllShows()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ShowResponse>>> =
                remoteDataSource.getAllShows()

            override fun saveCallResult(data: List<ShowResponse>) {
                val showList = DataMapper.mapResponsesShowToEntities(data)
                localDataSource.insertMovie(showList)
            }
        }.asLiveData()

    override fun getFavoriteMovie(): LiveData<List<Movie>> {
        return Transformations.map(localDataSource.getFavoriteMovie()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteShow(): LiveData<List<Movie>> {
        return Transformations.map(localDataSource.getFavoriteShow()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}