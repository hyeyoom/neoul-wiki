package wiki.neoul.api.authentication.adapter.inbound.external

interface RequesterPayload {
    fun getDisplayName(): String

    fun getUserId(): String
}
