package wiki.neoul.api.authentication.adapter.outbound.remote

import wiki.neoul.api.authentication.domain.Requester

interface RequesterRepository {
    fun saveAnonymousRequester(ipAddress: String): Requester
}
