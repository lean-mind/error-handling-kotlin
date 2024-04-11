package es.leanmind.errorhandling.domain

import es.leanmind.errorhandling.application.EmptyDataNotAllowedException
import es.leanmind.errorhandling.application.PasswordTooShortException

class User(
    username: String,
    password: String,
    role: UserRole = UserRole.STANDARD
) {
    val username: String
    val password: String
    val role: UserRole

    init {
        if (username.isEmpty() || password.isEmpty()) {
            throw EmptyDataNotAllowedException()
        }
        if (password.length < 8) {
            throw PasswordTooShortException()
        }

        this.username = username
        this.password = password
        this.role = role
    }
    fun isAdmin(): Boolean = role == UserRole.ADMIN
}

enum class UserRole {
    ADMIN, STANDARD
}
