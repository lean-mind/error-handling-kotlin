package es.leanmind.errorhandling.infra.http

import es.leanmind.errorhandling.domain.UserRole

data class UserDto(
        val username: String,
        val password: String,
        val role: UserRole,
)
