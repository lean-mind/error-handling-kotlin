package es.leanmind.errorhandling.exercise5

sealed class Either<out L, out R> {
    companion object {
        fun <L, R> right(value: R): Either<L, R> = Right(value)
        fun <L, R> left(value: L): Either<L, R> = Left(value)
    }

    fun <TR> map(f: (R) -> TR): Either<L, TR> = when (this) {
        is Right -> Right(f(value))
        is Left -> Left(value)
    }
    fun <TL> mapLeft(f: (L) -> TL): Either<TL, R> = when (this) {
        is Right -> Right(value)
        is Left -> Left(f(value))
    }
    fun <TR> flatMap(f: (R) -> Either<@UnsafeVariance L, TR>): Either<L, TR> = when(this) {
        is Right -> f(value)
        is Left -> Left(value)
    }
    fun <TL> flatMapLeft(f: (L) -> Either<TL, @UnsafeVariance R>): Either<TL, R> = when (this) {
        is Right -> Right(value)
        is Left -> f(value)
    }
    fun <T> match(ifRight: (R) -> T, ifLeft: (L) -> T): T = when (this) {
        is Right -> ifRight(value)
        is Left -> ifLeft(value)
    }

    private data class Right<out R>(val value: R): Either<Nothing, R>()
    private data class Left<L>(val value: L): Either<L, Nothing>()
}