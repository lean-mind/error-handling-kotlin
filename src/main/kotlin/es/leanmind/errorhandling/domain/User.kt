package es.leanmind.errorhandling.domain

import es.leanmind.errorhandling.application.Error
import es.leanmind.errorhandling.exercise2.Result

data class User private constructor(
    val username: String,
    val password: String,
    val role: UserRole = UserRole.STANDARD
) {
    fun isAdmin(): Boolean = role == UserRole.ADMIN

    companion object {
        fun create(username: String, password: String, role: UserRole = UserRole.STANDARD): Result<User> {
            if (username.isEmpty() || password.isEmpty()) {
                return Result.failure(Error.EmptyDataNotAllowed)
            }
            if (password.length < 8) {
                return Result.failure(Error.PasswordTooShort)
            }

            return Result.success(User(username, password, role))
        }
    }
}

enum class UserRole {
    ADMIN, STANDARD
}
