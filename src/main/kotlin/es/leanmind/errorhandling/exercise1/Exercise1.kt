package es.leanmind.errorhandling.exercise1

interface Value {
    fun value(): Any
}

class NumberValue(
        private val value: Int
): Value {
    override fun value(): Int = value
}

class StringValue(
        private val value: String
): Value {
    override fun value(): String = value
}

class ValueList<T : Value>(
        private val values: MutableList<T>
) : Value {
    fun add(value: T) {
        values.add(value)
    }

    fun filter(predicate: (T) -> Boolean): ValueList<T> {
        return ValueList(values.filter(predicate).toMutableList())
    }

    override fun value(): ValueList<T> {
        return ValueList(values)
    }

    override fun toString(): String {
        return "[${values.joinToString(", ") { it.value().toString() }}]"
    }
}

/*

public override string ToString()
{
    return String.Join(", ", values.Select(value => value.GetValue()));
}

*/