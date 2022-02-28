package wiki.neoul.api.authentication.adapter.outbound.remote

import org.springframework.stereotype.Repository
import wiki.neoul.api.authentication.domain.Requester

@Repository
class MockRequesterRepository : RequesterRepository {
    override fun saveAnonymousRequester(ipAddress: String): Requester {
        return Requester(
            id = Requester.RequesterId("localhost:8080"),
            displayName = "localhost:8080",
        )
    }
}
