package es.leanmind.errorhandling.application

import es.leanmind.errorhandling.domain.User
import es.leanmind.errorhandling.infra.database.UserRepository
import org.springframework.stereotype.Component

private const val MAX_NUMBER_OF_ADMINS = 2

@Component
class CreateUserUseCase(private val userRepository: UserRepository) {
    fun execute(user: User): CreateUserResult {
        if (userRepository.exists(user)) {
            return CreateUserResult.userAlreadyExistsError()
        }
        if (user.isAdmin() && cannotExistsMoreAdmins()) {
            return CreateUserResult.tooManyAdminsError()
        }
        return userRepository.save(user)
    }

    private fun cannotExistsMoreAdmins() = userRepository.countOfAdmins() >= MAX_NUMBER_OF_ADMINS
}