package wiki.neoul.api.authentication.application.port.outbound

import wiki.neoul.api.authentication.domain.Authentication

interface SaveAuthenticationPort {

    fun save(authentication: Authentication): Authentication.AuthenticationId
}
