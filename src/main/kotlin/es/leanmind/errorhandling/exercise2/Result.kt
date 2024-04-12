package es.leanmind.errorhandling.exercise2

import es.leanmind.errorhandling.application.Error

sealed interface Result<out T> {
    fun isSuccess(): Boolean = when (this) {
        is Success -> true
        is Failure -> false
    }
    fun isFailure(): Boolean = when (this) {
        is Success -> false
        is Failure -> true
    }
    fun value(): T? = null
    fun error(): Error? = null

    class Success<out T>(private val value: T) : Result<T> {
        override fun value(): T = value
    }

    class Failure(private val error: Error) : Result<Nothing> {
        override fun error(): Error = error
    }

    companion object {
        fun <T> success(value: T): Result<T> {
            return Success(value)
        }

        fun failure(error: Error): Result<Nothing> {
            return Failure(error)
        }
    }
}
