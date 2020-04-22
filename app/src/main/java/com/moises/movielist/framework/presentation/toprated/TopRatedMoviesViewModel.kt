package com.moises.movielist.framework.presentation.toprated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moises.movielist.core.arch.ScreenState
import com.moises.movielist.domain.toprated.usecase.GetAllTopRatedMoviesUseCase
import com.moises.movielist.framework.presentation.popular.PopularMoviesScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


class TopRatedMoviesViewModel(
    private val getAllTopRatedMoviesUseCase: GetAllTopRatedMoviesUseCase
) : ViewModel() {

    private lateinit var _topratedMoviesScreenState: MutableLiveData<ScreenState<TopRatedMoviesScreenState>>

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    val topratedMoviesScreenState: LiveData<ScreenState<TopRatedMoviesScreenState>>
        get() {
            if (!::_topratedMoviesScreenState.isInitialized) {
                _topratedMoviesScreenState = MutableLiveData()
                retrieveAllTopRatedMovies()
            }
            return _topratedMoviesScreenState
        }

    private fun retrieveAllTopRatedMovies() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _topratedMoviesScreenState.value = ScreenState.Loading
            val topratedMoviesResult =
                runCatching { getAllTopRatedMoviesUseCase.executeWithCoroutines(Unit) }
            topratedMoviesResult.onSuccess { topratedMoviesList ->
                _topratedMoviesScreenState.value =
                    ScreenState.Render(TopRatedMoviesScreenState.Movies(topratedMoviesList))
            }.onFailure { error ->
                _topratedMoviesScreenState.value = ScreenState.Render(
                    TopRatedMoviesScreenState.Error(
                        error.localizedMessage ?: "Ha ocurrido un error"
                    )
                )
            }
        }
    }
}