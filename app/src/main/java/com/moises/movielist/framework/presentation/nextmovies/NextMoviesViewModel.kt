package com.moises.movielist.framework.presentation.nextmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moises.movielist.core.arch.ScreenState
import com.moises.movielist.domain.nextmovies.usecase.GetAllNextMoviesCoroutineUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


class NextMoviesViewModel(
    private val getAllNextMoviesUseCase: GetAllNextMoviesCoroutineUseCase
) : ViewModel() {

    private lateinit var _nextMoviesScreenState: MutableLiveData<ScreenState<NextMoviesScreenState>>

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    val nextMoviesScreenState: LiveData<ScreenState<NextMoviesScreenState>>
        get() {
            if (!::_nextMoviesScreenState.isInitialized) {
                _nextMoviesScreenState = MutableLiveData()
                retrieveAllNextMovies()
            }
            return _nextMoviesScreenState
        }

    private fun retrieveAllNextMovies() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _nextMoviesScreenState.value = ScreenState.Loading
            val nextMoviesResult =
                runCatching { getAllNextMoviesUseCase.execute(Unit) }
            nextMoviesResult.onSuccess { nextMoviesList ->
                _nextMoviesScreenState.value =
                    ScreenState.Render(NextMoviesScreenState.NextMovies (nextMoviesList))
            }.onFailure { error ->
                _nextMoviesScreenState.value = ScreenState.Render(
                    NextMoviesScreenState.Error(
                        error.localizedMessage ?: "Ha ocurrido un error"
                    )
                )
            }
        }
    }
}