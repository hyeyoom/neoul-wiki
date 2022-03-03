package wiki.neoul.api.authentication.domain

import java.util.UUID

sealed class Authentication(
    val id: AuthenticationId,
    val requesterId: Requester.RequesterId,
    val authenticationType: AuthenticationType,
) {
    class AuthenticationId(val value: String)

    enum class AuthenticationType {
        EMAIL,
        ANONYMOUS
    }

    // FIXME: Not implemented yet
    class EmailAuthentication(
        id: AuthenticationId,
        requesterId: Requester.RequesterId,
    ) : Authentication(id, requesterId, AuthenticationType.EMAIL)

    class Anonymous(
        id: AuthenticationId,
        requesterId: Requester.RequesterId,
    ) : Authentication(id, requesterId, AuthenticationType.ANONYMOUS) {
        companion object {
            fun createNewAnonymousUser(requesterId: Requester.RequesterId): Anonymous =
                Anonymous(AuthenticationId(UUID.randomUUID().toString()), requesterId)
        }
    }
}
