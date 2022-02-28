package wiki.neoul.api.authentication.adapter.outbound.remote

import org.springframework.stereotype.Component
import wiki.neoul.api.authentication.application.port.outbound.SaveAnonymousUserPort
import wiki.neoul.api.authentication.domain.Requester

@Component
class RequesterAdapter(
    private val repository: RequesterRepository
) : SaveAnonymousUserPort {

    override fun saveAnonymousUser(ipAddress: String): Requester {
        return repository.saveAnonymousRequester(ipAddress)
    }
}
