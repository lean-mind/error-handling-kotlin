package es.leanmind.errorhandling.infra.http

import es.leanmind.errorhandling.application.CreateUserUseCase
import es.leanmind.errorhandling.application.EmptyDataNotAllowedException
import es.leanmind.errorhandling.application.Error
import es.leanmind.errorhandling.application.PasswordTooShortException
import es.leanmind.errorhandling.domain.User
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
        return try {
            val createUserResult = createUserUseCase.execute(userDto.toDomain())
            return when (createUserResult.error) {
                Error.UserAlreadyExists -> status(BAD_REQUEST).body("User already exists.")
                Error.TooManyAdmins -> status(BAD_REQUEST).body("Too many admins.")
                Error.CannotSaveUser -> status(INTERNAL_SERVER_ERROR).body("Cannot create user.")
                null -> status(CREATED).build<Unit>()
            }
        } catch (exception: PasswordTooShortException) {
            status(BAD_REQUEST).body("Password is too short.")
        } catch (exception: EmptyDataNotAllowedException) {
            status(BAD_REQUEST).body("Username and password cannot be empty.")
        }
    }

    private fun UserDto.toDomain(): User {
        return User(this.username, this.password, this.role)
    }
}
