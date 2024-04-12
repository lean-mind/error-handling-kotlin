package es.leanmind.errorhandling.application

sealed interface Error {
    data object UserAlreadyExists: Error
    data object TooManyAdmins: Error
    data object CannotSaveUser: Error
    data object EmptyDataNotAllowed : Error
    data object PasswordTooShort : Error
}

class CreateUserResult private constructor(
        val error: Error?,
) {
    fun isSuccess(): Boolean {
        return error === null
    }

    companion object {
        fun success(): CreateUserResult {
            return CreateUserResult(null)
        }

        fun userAlreadyExistsError(): CreateUserResult {
            return CreateUserResult(Error.UserAlreadyExists)
        }

        fun tooManyAdminsError(): CreateUserResult {
            return CreateUserResult(Error.TooManyAdmins)
        }

        fun cannotSaveUser(): CreateUserResult {
            return CreateUserResult(Error.CannotSaveUser)
        }
    }
}
