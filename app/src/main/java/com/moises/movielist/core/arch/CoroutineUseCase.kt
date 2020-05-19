package com.moises.movielist.core.arch

abstract class CoroutineUseCase<Params, ReturnValue> {
    abstract suspend fun execute(params: Params?) : ReturnValue
}