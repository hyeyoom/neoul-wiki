package wiki.neoul.api.document.adapter.outbound.remote

import wiki.neoul.api.document.domain.Author
import wiki.neoul.api.document.domain.AuthorId

interface AuthorRepository {
    fun loadById(id: AuthorId): Author?
}
