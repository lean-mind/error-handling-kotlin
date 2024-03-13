package es.leanmind.errorhandling

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ErrorHandlingApplication

fun main(args: Array<String>) {
    runApplication<ErrorHandlingApplication>(*args)
}
