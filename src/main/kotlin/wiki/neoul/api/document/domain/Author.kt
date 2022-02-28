package wiki.neoul.api.document.domain

data class AuthorId(val id: String)

data class Author(
    val id: AuthorId,
    val name: String,
)
