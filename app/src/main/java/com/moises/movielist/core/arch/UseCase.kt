package com.moises.movielist.core.arch

abstract class UseCase<Params, ReturnValue> {
    abstract suspend fun executeWithCoroutines(params: Params?) : ReturnValue
}