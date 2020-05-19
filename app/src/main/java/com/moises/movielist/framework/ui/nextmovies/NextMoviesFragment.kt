package com.moises.movielist.framework.ui.nextmovies

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
import com.moises.movielist.domain.nextmovies.model.NextMovie
import com.moises.movielist.core.ui.GenericDataBindingAdapter
import com.moises.movielist.framework.presentation.nextmovies.NextMoviesScreenState
import com.moises.movielist.framework.presentation.nextmovies.NextMoviesViewModel
import kotlinx.android.synthetic.main.fragment_next_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NextMoviesFragment : Fragment() {

    private val nextMoviesViewModel: NextMoviesViewModel by viewModel()
    private lateinit var nextMoviesAdapter: GenericDataBindingAdapter<NextMovie>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nextMoviesViewModel.nextMoviesScreenState.observe(
            viewLifecycleOwner,
            Observer { screenState ->
                renderScreenState(screenState)
            })
        bindViews()
    }

    private fun bindViews() {
        nextMoviesAdapter =
            GenericDataBindingAdapter(
                BR.nextmovie,
                R.layout.next_movie_element
            )
        rvNextMovies?.apply {
            adapter = nextMoviesAdapter

            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)
        }
    }
    private fun renderScreenState(screenState: ScreenState<NextMoviesScreenState>) {
        when (screenState) {
            ScreenState.Loading -> showLoader()
            is ScreenState.Render -> renderInformation(screenState.data)
        }
    }

    private fun renderInformation(nextMoviesScreenState: NextMoviesScreenState) {
        hideLoader()
        when (nextMoviesScreenState) {
            is NextMoviesScreenState.Error -> showError(nextMoviesScreenState.message)
            is NextMoviesScreenState.NextMovies -> showMovies(nextMoviesScreenState.list)
        }
    }

    private fun showLoader() {
        pbNextMovies.visibility=View.VISIBLE
    }

    private fun hideLoader() {
        pbNextMovies.visibility=View.GONE
    }

    private fun showError(message : String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun showMovies(list: List<NextMovie>) {
        nextMoviesAdapter.setItems(
            list.toMutableList()
        )
    }
}
