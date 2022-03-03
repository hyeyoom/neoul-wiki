package wiki.neoul.api.authentication.application.port.outbound

import wiki.neoul.api.authentication.domain.Requester

interface LoadRequesterPort {
    fun loadByRequesterId(id: Requester.RequesterId): Requester?
}
