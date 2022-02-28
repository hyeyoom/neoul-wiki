package wiki.neoul.api.document.adapter.inbound.external

interface AuthorPayload {
    fun getDisplayName(): String

    fun getUserId(): String
}
