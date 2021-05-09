package com.interview.winvesta.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.interview.winvesta.data.model.Movie
import com.interview.winvesta.data.repository.MoviesRepository

class MovieViewModel:ViewModel() {

   val moviesRepository:MoviesRepository= MoviesRepository();

    private var currentQueryValue: String? = null
    private var currentFetchResult: LiveData<PagingData<Movie>>? = null

    fun fetchPopularMovies(queryString: String): LiveData<PagingData<Movie>> {
        currentQueryValue = queryString
        val newResult: LiveData<PagingData<Movie>> =
            moviesRepository.getPopularMovieResultStream(queryString).cachedIn(viewModelScope)
        currentFetchResult = newResult
        return newResult
    }




}