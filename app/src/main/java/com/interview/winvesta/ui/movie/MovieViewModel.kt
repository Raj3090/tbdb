package com.interview.winvesta.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.interview.winvesta.data.model.Movie
import com.interview.winvesta.data.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MovieViewModel:ViewModel() {

   val moviesRepository:MoviesRepository= MoviesRepository();

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Movie>>? = null

    fun fetchPopularMovies(queryString: String): Flow<PagingData<Movie>> {
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Movie>> =
            moviesRepository.getPopularMovieResultStream(queryString).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }


}