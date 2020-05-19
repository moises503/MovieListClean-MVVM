package com.moises.movielist.framework.presentation.toprated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moises.movielist.core.arch.ScreenState
import com.moises.movielist.domain.toprated.usecase.GetAllTopRatedMoviesCoroutineUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


class TopRatedMoviesViewModel(
    private val getAllTopRatedMoviesUseCase: GetAllTopRatedMoviesCoroutineUseCase
) : ViewModel() {

    private lateinit var _topRatedMoviesScreenState: MutableLiveData<ScreenState<TopRatedMoviesScreenState>>

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    val topRatedMoviesScreenState: LiveData<ScreenState<TopRatedMoviesScreenState>>
        get() {
            if (!::_topRatedMoviesScreenState.isInitialized) {
                _topRatedMoviesScreenState = MutableLiveData()
                retrieveAllTopRatedMovies()
            }
            return _topRatedMoviesScreenState
        }

    private fun retrieveAllTopRatedMovies() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _topRatedMoviesScreenState.value = ScreenState.Loading
            val topRatedMoviesResult =
                runCatching { getAllTopRatedMoviesUseCase.execute(Unit) }
            topRatedMoviesResult.onSuccess { topratedMoviesList ->
                _topRatedMoviesScreenState.value =
                    ScreenState.Render(TopRatedMoviesScreenState.Movies(topratedMoviesList))
            }.onFailure { error ->
                _topRatedMoviesScreenState.value = ScreenState.Render(
                    TopRatedMoviesScreenState.Error(
                        error.localizedMessage ?: "Ha ocurrido un error"
                    )
                )
            }
        }
    }
}