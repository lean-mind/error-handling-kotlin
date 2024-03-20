package es.leanmind.errorhandling.exercise1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Exercise1Should {
    @Test
    fun work() {
        val numberValue1 = NumberValue(1)
        val numberValue2 = NumberValue(2)

        val stringValue1 = StringValue("hello")
        val stringValue2 = StringValue("world")

        val values = ValueList(mutableListOf(numberValue1, numberValue2, stringValue1, stringValue2))

        values.add(StringValue("foo"))

        values.add(ValueList(mutableListOf(numberValue1, numberValue2)))

        val numberValues = values.filter { it is NumberValue }
        val stringValues = values.filter { it is StringValue }

        assertThat(values.toString()).isEqualTo("[1, 2, hello, world, foo, [1, 2]]")
        assertThat(numberValues.toString()).isEqualTo("[1, 2]")
        assertThat(stringValues.toString()).isEqualTo("[hello, world, foo]")
    }
}
