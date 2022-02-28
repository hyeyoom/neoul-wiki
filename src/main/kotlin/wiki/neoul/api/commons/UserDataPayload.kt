package wiki.neoul.api.commons

import wiki.neoul.api.authentication.adapter.inbound.external.RequesterPayload
import wiki.neoul.api.document.adapter.inbound.external.AuthorPayload

class UserDataPayload(
    private val displayName: String,
    private val userId: String,
) : AuthorPayload, RequesterPayload {
    override fun getDisplayName(): String {
        return displayName
    }

    override fun getUserId(): String {
        return userId
    }
}
