package com.nicktra.moviedex.core.data

import com.nicktra.moviedex.core.data.source.local.LocalDataSource
import com.nicktra.moviedex.core.data.source.remote.RemoteDataSource
import com.nicktra.moviedex.core.data.source.remote.network.ApiResponse
import com.nicktra.moviedex.core.data.source.remote.response.MovieResponse
import com.nicktra.moviedex.core.data.source.remote.response.ShowResponse
import com.nicktra.moviedex.core.domain.model.Movie
import com.nicktra.moviedex.core.domain.repository.IMovieRepository
import com.nicktra.moviedex.core.utils.AppExecutors
import com.nicktra.moviedex.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesMovieToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getAllShows(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<ShowResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllShows().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ShowResponse>>> =
                remoteDataSource.getAllShows()

            override suspend fun saveCallResult(data: List<ShowResponse>) {
                val showList = DataMapper.mapResponsesShowToEntities(data)
                localDataSource.insertMovie(showList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteShow(): Flow<List<Movie>> {
        return localDataSource.getFavoriteShow().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}