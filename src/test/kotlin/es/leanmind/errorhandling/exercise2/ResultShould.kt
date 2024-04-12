package es.leanmind.errorhandling.exercise2

import es.leanmind.errorhandling.application.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultShould {
    @Test
    fun buildASuccessResult() {
        val result = Result.success("irrelevant")

        assertThat(result.isSuccess()).isTrue()
        assertThat(result.value()).isEqualTo("irrelevant")
    }

    @Test
    fun buildAnErrorResult() {
        val result = Result.failure(Error.TooManyAdmins)

        assertThat(result.isFailure()).isTrue()
        assertThat(result.error()).isEqualTo(Error.TooManyAdmins)
    }
}
