package wiki.neoul.api.authentication.adapter.outbound.persistence

import wiki.neoul.api.authentication.adapter.outbound.persistence.entity.AuthenticationEntity
import wiki.neoul.api.authentication.application.port.outbound.SaveAuthenticationPort
import wiki.neoul.api.authentication.application.port.outbound.SearchAuthenticationPort
import wiki.neoul.api.authentication.domain.Authentication
import wiki.neoul.api.authentication.domain.Requester
import wiki.neoul.api.commons.annotations.PersistenceAdapter

@PersistenceAdapter
class AuthenticationPersistenceAdapter(
    private val repository: AuthenticationJpaRepository
) : SearchAuthenticationPort, SaveAuthenticationPort {

    override fun findByRequesterId(requesterId: Requester.RequesterId): Authentication? {
        return repository
            .findByRequesterId(requesterId.value)
            ?.let {
                return when (it.authenticationType) {
                    Authentication.AuthenticationType.EMAIL -> {
                        Authentication.EmailAuthentication(
                            Authentication.AuthenticationId(it.id),
                            Requester.RequesterId(it.requesterId)
                        )
                    }
                    Authentication.AuthenticationType.ANONYMOUS -> {
                        Authentication.Anonymous(
                            Authentication.AuthenticationId(it.id),
                            Requester.RequesterId(it.requesterId)
                        )
                    }
                }
            }
    }

    override fun save(authentication: Authentication): Authentication.AuthenticationId {
        val authenticationEntity = AuthenticationEntity(
            authentication.id.value,
            authentication.requesterId.value,
            authentication.authenticationType
        )

        val saved = repository.save(authenticationEntity)
        return Authentication.AuthenticationId(saved.id)
    }
}
