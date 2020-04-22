package com.moises.movielist.framework.ui.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.moises.movielist.BR
import com.moises.movielist.R
import com.moises.movielist.core.arch.ScreenState
import com.moises.movielist.domain.toprated.model.TopRatedMovie
import com.moises.movielist.framework.GenericDataBindingAdapter
import com.moises.movielist.framework.presentation.toprated.TopRatedMoviesScreenState
import com.moises.movielist.framework.presentation.toprated.TopRatedMoviesViewModel
import kotlinx.android.synthetic.main.fragment_top_rated_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopRatedMoviesFragment : Fragment() {

    private val topratedMoviesViewModel: TopRatedMoviesViewModel by viewModel()
    private lateinit var topratedMoviesAdapter: GenericDataBindingAdapter<TopRatedMovie>
    private var topratedmovies : MutableList<TopRatedMovie> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_rated_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topratedMoviesViewModel.topratedMoviesScreenState.observe(
            viewLifecycleOwner,
            Observer { screenState ->
                renderScreenState(screenState)
            })
        recyclerBind()
    }

    fun recyclerBind() {

        topratedMoviesAdapter =
            GenericDataBindingAdapter(BR.topratedmovie,
                R.layout.toprated_movie_element)
        rvTopRatedMovies?.apply {
            adapter = topratedMoviesAdapter

            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)
        }



    }
    private fun renderScreenState(screenState: ScreenState<TopRatedMoviesScreenState>) {
        when (screenState) {
            ScreenState.Loading -> showLoader()
            is ScreenState.Render -> renderInformation(screenState.data)
        }
    }

    private fun renderInformation(topratedMoviesScreenState: TopRatedMoviesScreenState) {
        hideLoader()
        when (topratedMoviesScreenState) {
            is TopRatedMoviesScreenState.Error -> showError(topratedMoviesScreenState.message)
            is TopRatedMoviesScreenState.Movies -> showMovies(topratedMoviesScreenState.list)
        }
    }

    private fun showLoader() {
        pbTopRatedMovies.visibility=View.VISIBLE
    }

    private fun hideLoader() {
        pbTopRatedMovies.visibility=View.GONE
    }

    private fun showError(message : String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun showMovies(list: List<TopRatedMovie>) {
        topratedmovies.clear()
        topratedmovies.addAll(list.toMutableList())

        topratedMoviesAdapter.setItems(
            topratedmovies
        )
     }
}
