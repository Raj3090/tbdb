package com.interview.winvesta.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.interview.winvesta.data.api.MoviePagingSource
import com.interview.winvesta.data.api.MovieService
import com.interview.winvesta.data.model.Movie
import kotlinx.coroutines.flow.Flow

class MoviesRepository() {

    private val movieService:MovieService=MovieService.create();

    fun getPopularMovieResultStream(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { MoviePagingSource(movieService, query) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

}