package wiki.neoul.api.document.application.port.outbound

import wiki.neoul.api.document.domain.Author
import wiki.neoul.api.document.domain.AuthorId

interface LoadAuthorPort {
    fun loadById(authorId: AuthorId): Author?
}
