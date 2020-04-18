package com.moises.movielist.framework.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.moises.movielist.BR
import com.moises.movielist.R
import com.moises.movielist.core.arch.ScreenState
import com.moises.movielist.domain.popular.model.Movie
import com.moises.movielist.framework.GenericDataBindingAdapter
import com.moises.movielist.framework.presentation.popular.PopularMoviesScreenState
import com.moises.movielist.framework.presentation.popular.PopularMoviesViewModel
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import uabjo.drti.eleccion.modules.common.framework.DataBindingVariables

class PopularMoviesFragment : Fragment() {

    private val popularMoviesViewModel: PopularMoviesViewModel by viewModel()
    private lateinit var popularMoviesAdapter: GenericDataBindingAdapter<Movie>
    private var popularmovies : MutableList<Movie> = mutableListOf()

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
            recyclerBind()
    }

    fun recyclerBind() {
        popularMoviesAdapter =
            GenericDataBindingAdapter(BR.movie,
                R.layout.popular_movie_element)
        rvPopularMovies?.apply {
            adapter = popularMoviesAdapter

            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)
        }
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
        pbPopularMovies.visibility=View.VISIBLE
    }

    private fun hideLoader() {
        pbPopularMovies.visibility=View.GONE
    }

    private fun showError(message : String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun showMovies(list: List<Movie>) {
        popularmovies.clear()
        popularmovies.addAll(list.toMutableList())

        popularMoviesAdapter.setItems(
            popularmovies
        )
        //Toast.makeText(requireContext(), "Total de películas: ${list.size}", Toast.LENGTH_LONG).show()


    }
}
