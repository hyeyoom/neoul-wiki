package wiki.neoul.api.authentication.application.service

import wiki.neoul.api.authentication.application.port.inbound.SearchAuthenticationUseCase
import wiki.neoul.api.authentication.application.port.outbound.SearchAuthenticationPort
import wiki.neoul.api.authentication.domain.Authentication
import wiki.neoul.api.authentication.domain.Requester
import wiki.neoul.api.commons.annotations.UseCase

@UseCase
class SearchAuthenticationService(
    private val searchAuthenticationPort: SearchAuthenticationPort
) : SearchAuthenticationUseCase {

    override fun execute(query: SearchAuthenticationUseCase.SearchAuthenticationQuery): SearchAuthenticationUseCase.SearchAuthenticationResult {
        return when (
            val result = searchAuthenticationPort.findByRequesterId(Requester.RequesterId(query.userId ?: query.ipAddress))
        ) {
            is Authentication.Anonymous -> {
                SearchAuthenticationUseCase.SearchAuthenticationResult.AnonymousUser(
                    userId = result.requesterId.value,
                    displayName = result.displayName,
                )
            }
            is Authentication.Authenticated -> {
                SearchAuthenticationUseCase.SearchAuthenticationResult.IdentifiedUser(
                    userId = result.requesterId.value,
                    displayName = result.displayName
                )
            }
            null -> SearchAuthenticationUseCase.SearchAuthenticationResult.UnidentifiedUser
        }
    }
}
