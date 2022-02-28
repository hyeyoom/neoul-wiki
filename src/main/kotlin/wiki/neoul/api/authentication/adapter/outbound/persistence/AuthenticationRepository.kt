package wiki.neoul.api.authentication.adapter.outbound.persistence

import wiki.neoul.api.authentication.domain.Authentication
import wiki.neoul.api.authentication.domain.Requester

interface AuthenticationRepository {
    fun findByRequesterId(requesterId: Requester.RequesterId): Authentication?
    fun save(authentication: Authentication): Authentication.AuthenticationId
}
