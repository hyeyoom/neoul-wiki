package wiki.neoul.api.authentication.application.port.outbound

import wiki.neoul.api.authentication.domain.Requester

interface SaveAnonymousUserPort {
    fun saveAnonymousUser(ipAddress: String): Requester
}
