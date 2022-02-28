package wiki.neoul.api.authentication.adapter.outbound.persistence

import wiki.neoul.api.authentication.application.port.outbound.SaveAuthenticationPort
import wiki.neoul.api.authentication.application.port.outbound.SearchAuthenticationPort
import wiki.neoul.api.authentication.domain.Authentication
import wiki.neoul.api.authentication.domain.Requester
import wiki.neoul.api.commons.annotations.PersistenceAdapter

@PersistenceAdapter
class AuthenticationPersistenceAdapter(
    private val repository: AuthenticationRepository
) : SearchAuthenticationPort, SaveAuthenticationPort {

    override fun findByRequesterId(requesterId: Requester.RequesterId): Authentication? {
        return repository.findByRequesterId(requesterId)
    }

    override fun save(authentication: Authentication): Authentication.AuthenticationId {
        return repository.save(authentication)
    }
}
