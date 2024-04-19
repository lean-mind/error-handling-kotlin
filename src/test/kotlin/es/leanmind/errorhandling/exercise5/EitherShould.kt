package es.leanmind.errorhandling.exercise5

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class EitherShould {
    @Test
    fun `build a right value`() {
        val value = Either.right<String, Int>(1)

        value.match(
            ifRight = { Assertions.assertThat(it).isEqualTo(1) },
            ifLeft = { Assertions.fail("Should be right, got left") },
        )
    }

    @Test
    fun `build a left value`() {
        val value = Either.left<String, Int>("10")

        value.match(
            ifLeft = { Assertions.assertThat(it).isEqualTo("10") },
            ifRight = { Assertions.fail("Should be left, got right") },
        )
    }

    @Test
    fun `map the right value`() {
        val value = Either.right<String, Int>(1)

        val transformedValue = value.map(Int::toString)

        transformedValue.match(
            ifRight = { Assertions.assertThat(it).isEqualTo("1") },
            ifLeft = { Assertions.fail("Should be right, got left") }
        )
    }

    @Test
    fun `map the left value`() {
        val value = Either.left<String, Int>("10")

        val transformedValue = value.mapLeft(String::toInt)

        transformedValue.match(
            ifLeft = { Assertions.assertThat(it).isEqualTo(10) },
            ifRight = { Assertions.fail("Should be left, got right") }
        )
    }

    @Test
    fun `flat map the right value`() {
        val value = Either.right<String, Int>(1)

        val transformedValue = value.flatMap { Either.right(it.toString()) }

        transformedValue.match(
            ifRight = { Assertions.assertThat(it).isEqualTo("1") },
            ifLeft = { Assertions.fail("Should be right, got left") }
        )
    }

    @Test
    fun `flat map the left value`() {
        val value = Either.left<String, Int>("10")

        val transformedValue = value.flatMapLeft { Either.left(it.toInt()) }

        transformedValue.match(
            ifLeft = { Assertions.assertThat(it).isEqualTo(10) },
            ifRight = { Assertions.fail("Should be left, got right") }
        )
    }
}