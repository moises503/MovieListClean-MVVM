package com.moises.movielist.framework.presentation.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moises.movielist.core.arch.ScreenState
import com.moises.movielist.domain.popular.usecase.GetAllPopularMoviesCoroutineUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PopularMoviesViewModel(
    private val getAllPopularMoviesUseCase: GetAllPopularMoviesCoroutineUseCase
) : ViewModel() {

    private lateinit var _popularMoviesScreenState: MutableLiveData<ScreenState<PopularMoviesScreenState>>

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    val popularMoviesScreenState: LiveData<ScreenState<PopularMoviesScreenState>>
        get() {
            if (!::_popularMoviesScreenState.isInitialized) {
                _popularMoviesScreenState = MutableLiveData()
                retrieveAllPopularMovies()
            }
            return _popularMoviesScreenState
        }

    private fun retrieveAllPopularMovies() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _popularMoviesScreenState.value = ScreenState.Loading
            val popularMoviesResult =
                runCatching { getAllPopularMoviesUseCase.execute(Unit) }
            popularMoviesResult.onSuccess { popularMoviesList ->
                _popularMoviesScreenState.value =
                    ScreenState.Render(PopularMoviesScreenState.Movies(popularMoviesList))
            }.onFailure { error ->
                _popularMoviesScreenState.value = ScreenState.Render(
                    PopularMoviesScreenState.Error(
                        error.localizedMessage ?: "Ha ocurrido un error"
                    )
                )
            }
        }
    }
}