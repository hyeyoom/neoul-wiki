package wiki.neoul.api.authentication.application.port.inbound

interface SearchAuthenticationUseCase {

    fun execute(query: SearchAuthenticationQuery): SearchAuthenticationResult

    data class SearchAuthenticationQuery(
        val userId: String?,
        val ipAddress: String,
    )

    sealed class SearchAuthenticationResult {
        class IdentifiedUser(
            val userId: String,
            val displayName: String,
        ) : SearchAuthenticationResult()

        class AnonymousUser(
            val userId: String,
            val displayName: String,
        ) : SearchAuthenticationResult()

        object UnidentifiedUser : SearchAuthenticationResult()
    }
}
