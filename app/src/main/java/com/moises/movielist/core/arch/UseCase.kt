package com.moises.movielist.core.arch

abstract class UseCase<Params, ReturnValue> {
    abstract fun execute(params : Params?) : ReturnValue
}