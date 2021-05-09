package com.interview.winvesta.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.interview.winvesta.R
import com.interview.winvesta.databinding.ActivityMainBinding
import com.interview.winvesta.ui.movie.MovieViewModel
import com.interview.winvesta.ui.movie.MoviesAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    val  POPULAR_MOVIES:String = "popularity.desc"

    private val adapter = MoviesAdapter()
    private var searchJob: Job? = null
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.moviesRecyclerView.adapter = adapter;

        fetch(POPULAR_MOVIES);
    }


    private fun fetch(query: String) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.fetchPopularMovies(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

}