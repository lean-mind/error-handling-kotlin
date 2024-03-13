package es.leanmind.errorhandling.infra.database

import es.leanmind.errorhandling.application.CreateUserResult
import es.leanmind.errorhandling.domain.User
import es.leanmind.errorhandling.domain.UserRole
import org.springframework.stereotype.Service

@Service
class UserRepository {
    private val users = mutableListOf<User>()

    fun exists(user: User): Boolean {
        return users.find { it.username == user.username } !== null
    }

    fun save(user: User): CreateUserResult {
        try {
            users.add(user)
            return CreateUserResult.success()
        } catch (exception: Exception) {
            return CreateUserResult.cannotSaveUser()
        }

    }

    fun countOfAdmins(): Int {
        return users.count { it.role == UserRole.ADMIN }
    }
}
