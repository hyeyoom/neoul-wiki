package wiki.neoul.api.authentication.domain

import java.util.UUID

sealed class Authentication(
    val id: AuthenticationId,
    val requesterId: Requester.RequesterId,
    val displayName: String
) {
    class AuthenticationId(val value: String)

    class Authenticated(
        id: AuthenticationId,
        requesterId: Requester.RequesterId,
        userName: String,
    ) : Authentication(id, requesterId, userName)

    class Anonymous(
        id: AuthenticationId,
        requesterId: Requester.RequesterId,
        ipAddress: String,
    ) : Authentication(id, requesterId, ipAddress) {
        companion object {
            fun createNewAnonymousUser(requesterId: Requester.RequesterId, ipAddress: String): Anonymous =
                Anonymous(AuthenticationId(UUID.randomUUID().toString()), requesterId, ipAddress)
        }
    }
}
