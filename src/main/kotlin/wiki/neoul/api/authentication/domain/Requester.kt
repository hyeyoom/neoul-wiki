package wiki.neoul.api.authentication.domain

data class Requester(
    val id: RequesterId,
    val displayName: String,
) {
    data class RequesterId(val value: String)
}
