package es.leanmind.errorhandling.infra.http

import es.leanmind.errorhandling.application.CreateUserUseCase
import es.leanmind.errorhandling.application.Error
import es.leanmind.errorhandling.domain.User
import es.leanmind.errorhandling.exercise2.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private lateinit var createUserUseCase: CreateUserUseCase

    @PostMapping
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<*> {
        val userResult = userDto.toDomain()

        if (userResult.isFailure()) {
            when (userResult.error()) {
                Error.EmptyDataNotAllowed -> status(BAD_REQUEST).body("Username and password cannot be empty.")
                Error.PasswordTooShort -> status(BAD_REQUEST).body("Password is too short.")
                else -> {}
            }
        }

        val createUserResult = createUserUseCase.execute(userResult.value()!!)
        return when (createUserResult.error) {
            Error.UserAlreadyExists -> status(BAD_REQUEST).body("User already exists.")
            Error.TooManyAdmins -> status(BAD_REQUEST).body("Too many admins.")
            Error.CannotSaveUser -> status(INTERNAL_SERVER_ERROR).body("Cannot create user.")
            else -> status(CREATED).build<Unit>()
        }
    }

    private fun UserDto.toDomain(): Result<User> {
        return User.create(this.username, this.password, this.role)
    }
}
