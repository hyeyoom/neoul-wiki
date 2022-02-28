package wiki.neoul.api.authentication.application.port.outbound

import wiki.neoul.api.authentication.domain.Authentication
import wiki.neoul.api.authentication.domain.Requester

interface SearchAuthenticationPort {
    fun findByRequesterId(requesterId: Requester.RequesterId): Authentication?
}
