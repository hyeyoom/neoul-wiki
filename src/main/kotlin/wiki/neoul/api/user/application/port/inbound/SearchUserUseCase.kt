package wiki.neoul.api.user.application.port.inbound

interface SearchUserUseCase {

    fun execute(query: SearchUserQuery): SearchUserQueryResult

    data class SearchUserQuery(
        val userId: String,
    )

    data class SearchUserQueryResult(
        val userName: String,
        val userEmail: String,
    )

    sealed class SearchUserUseCaseException(message: String) : RuntimeException(message) {
        class UserNotFoundException(id: String) : SearchUserUseCaseException(
            "User not found. Id: $id"
        )
    }
}
