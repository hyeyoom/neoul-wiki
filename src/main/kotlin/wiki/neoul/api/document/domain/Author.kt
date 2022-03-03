package wiki.neoul.api.document.domain

// FIXME: AuthorId Author 안으로 이동 필요
data class AuthorId(val value: String)

data class Author(
    val id: AuthorId,
    val name: String,
)
