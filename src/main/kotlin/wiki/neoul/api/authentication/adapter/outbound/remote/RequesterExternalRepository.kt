package wiki.neoul.api.authentication.adapter.outbound.remote

import wiki.neoul.api.authentication.domain.Requester

/**
 * Interface to be implemented by external package on user domain
 */
interface RequesterExternalRepository {
    fun saveAnonymousRequester(ipAddress: String): Requester
    fun loadByRequesterId(id: Requester.RequesterId): Requester?
}
