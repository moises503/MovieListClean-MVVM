package com.moises.movielist.core.arch

import java.lang.UnsupportedOperationException

abstract class Transformer<T1, T2> {
    abstract fun transform(value : T1) : T2
    open fun reverseTransform(value: T2) : T1 = throw UnsupportedOperationException()
    fun transformCollection(values : List<T1>) = values.map { t1 -> transform(t1) }
    fun reverseTransformCollection(values: List<T2>) = values.map { t2 -> reverseTransform(t2)}
}