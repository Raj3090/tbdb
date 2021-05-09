package com.interview.winvesta.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.interview.winvesta.data.model.Movie

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class MoviePagingSource(

    private val service: MovieService,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = service.getPopularMovies(query, page)
            val movies = response.results
            LoadResult.Page(
                data = movies,
                prevKey = if (page == UNSPLASH_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.total_results) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }
}