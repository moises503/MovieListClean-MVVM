package com.moises.movielist.framework.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.moises.movielist.R
import com.moises.movielist.core.arch.ScreenState
import com.moises.movielist.domain.popular.model.Movie
import com.moises.movielist.framework.presentation.popular.PopularMoviesScreenState
import com.moises.movielist.framework.presentation.popular.PopularMoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularMoviesFragment : Fragment() {

    private val popularMoviesViewModel: PopularMoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularMoviesViewModel.popularMoviesScreenState.observe(
            viewLifecycleOwner,
            Observer { screenState ->
                renderScreenState(screenState)
            })
    }

    private fun renderScreenState(screenState: ScreenState<PopularMoviesScreenState>) {
        when (screenState) {
            ScreenState.Loading -> showLoader()
            is ScreenState.Render -> renderInformation(screenState.data)
        }
    }

    private fun renderInformation(popularMoviesScreenState: PopularMoviesScreenState) {
        hideLoader()
        when (popularMoviesScreenState) {
            is PopularMoviesScreenState.Error -> showError(popularMoviesScreenState.message)
            is PopularMoviesScreenState.Movies -> showMovies(popularMoviesScreenState.list)
        }
    }

    private fun showLoader() {
        showError("Mostrando loader")
    }

    private fun hideLoader() {
        showError("Oculando loader")
    }

    private fun showError(message : String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun showMovies(list: List<Movie>) {
        Toast.makeText(requireContext(), "Total de películas: ${list.size}", Toast.LENGTH_LONG).show()
    }
}
