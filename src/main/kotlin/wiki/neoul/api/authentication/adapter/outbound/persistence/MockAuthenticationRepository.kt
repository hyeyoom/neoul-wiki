package wiki.neoul.api.authentication.adapter.outbound.persistence

import org.springframework.stereotype.Repository
import wiki.neoul.api.authentication.domain.Authentication
import wiki.neoul.api.authentication.domain.Requester

@Repository
class MockAuthenticationRepository : AuthenticationRepository {
    private val db = HashMap<Requester.RequesterId, Authentication>()

    override fun findByRequesterId(requesterId: Requester.RequesterId): Authentication? = db[requesterId]

    override fun save(authentication: Authentication): Authentication.AuthenticationId {
        val id = authentication.requesterId
        db[id] = authentication
        return authentication.id
    }
}
