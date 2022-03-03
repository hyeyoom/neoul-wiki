package wiki.neoul.api.authentication.application.service

import wiki.neoul.api.authentication.application.port.inbound.SearchAuthenticationUseCase
import wiki.neoul.api.authentication.application.port.outbound.LoadRequesterPort
import wiki.neoul.api.authentication.application.port.outbound.SearchAuthenticationPort
import wiki.neoul.api.authentication.domain.Authentication
import wiki.neoul.api.authentication.domain.Requester
import wiki.neoul.api.commons.annotations.UseCase

@UseCase
class SearchAuthenticationService(
    private val searchAuthenticationPort: SearchAuthenticationPort,
    private val loadRequesterPort: LoadRequesterPort,
) : SearchAuthenticationUseCase {

    override fun execute(query: SearchAuthenticationUseCase.SearchAuthenticationQuery): SearchAuthenticationUseCase.SearchAuthenticationResult {
        return when (
            val result = searchAuthenticationPort.findByRequesterId(Requester.RequesterId(query.userId ?: query.ipAddress))
        ) {
            is Authentication.Anonymous -> {
                val requester = loadRequester(result.requesterId)
                SearchAuthenticationUseCase.SearchAuthenticationResult.AnonymousUser(
                    userId = result.requesterId.value,
                    displayName = requester.displayName
                )
            }
            is Authentication.EmailAuthentication -> {
                val requester = loadRequester(result.requesterId)
                SearchAuthenticationUseCase.SearchAuthenticationResult.IdentifiedUser(
                    userId = result.requesterId.value,
                    displayName = requester.displayName
                )
            }
            null -> SearchAuthenticationUseCase.SearchAuthenticationResult.UnidentifiedUser
        }
    }

    private fun loadRequester(requesterId: Requester.RequesterId) =
        loadRequesterPort
            .loadByRequesterId(requesterId)
            ?: error("Fatal error detected. Authentication exists but user is gone.")
}
