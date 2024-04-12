package es.leanmind.errorhandling.exercise4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Exercise4Should {
    @Test
    fun ex1Filtering() {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val greaterThan5Numbers = numbers.filter(::greaterThan5)
        val oddNumbers = numbers.filter(::isOdd)
        val evenNumbers = numbers.filter(::isEven)

        assertThat(greaterThan5Numbers).containsExactly(6, 7, 8, 9, 10)
        assertThat(oddNumbers).containsExactly(1, 3, 5, 7, 9)
        assertThat(evenNumbers).containsExactly(2, 4, 6, 8, 10)
    }

    private fun greaterThan5(i: Int): Boolean {
        return i > 5
    }
    private fun isOdd(i: Int): Boolean {
        return i % 2 != 0
    }
    private fun isEven(i: Int): Boolean {
        return i % 2 == 0
    }

    @Test
    fun currying() {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val greaterThan5Numbers = numbers.filter(isGreaterThan(5))
        val divisibleBy3Numbers = numbers.filter(isDivisibleBy(3))

        assertThat(greaterThan5Numbers).containsExactly(6, 7, 8, 9, 10)
        assertThat(divisibleBy3Numbers).containsExactly(3, 6, 9)
    }

    private fun isGreaterThan(i: Int): (Int) -> Boolean {
        return { number: Int -> number > i }
    }
    private fun isDivisibleBy(i: Int): (Int) -> Boolean {
        return { number: Int -> number % i == 0 }
    }
}
