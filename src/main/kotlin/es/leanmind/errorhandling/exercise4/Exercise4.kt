package es.leanmind.errorhandling.exercise4

fun sum(a: Int, b: Int, printer: (Int) -> Unit) {
    printer(a + b)
}

fun consoleOutput(value: Int) {
    println(value)
}

fun main() {
    sum(1, 2, ::consoleOutput)
}
