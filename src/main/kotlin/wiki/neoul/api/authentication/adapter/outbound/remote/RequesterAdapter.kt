package wiki.neoul.api.authentication.adapter.outbound.remote

import org.springframework.stereotype.Component
import wiki.neoul.api.authentication.application.port.outbound.LoadRequesterPort
import wiki.neoul.api.authentication.application.port.outbound.SaveAnonymousUserPort
import wiki.neoul.api.authentication.domain.Requester

@Component
class RequesterAdapter(
    private val repository: RequesterExternalRepository
) : SaveAnonymousUserPort, LoadRequesterPort {

    override fun saveAnonymousUser(ipAddress: String): Requester {
        return repository.saveAnonymousRequester(ipAddress)
    }

    override fun loadByRequesterId(id: Requester.RequesterId): Requester? {
        return repository.loadByRequesterId(id)
    }
}
