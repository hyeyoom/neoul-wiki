package wiki.neoul.api.document.adapter.outbound.remote

import org.springframework.stereotype.Repository
import wiki.neoul.api.document.domain.Author
import wiki.neoul.api.document.domain.AuthorId
import java.util.UUID

@Repository
class MockAuthorRepository : AuthorRepository {
    override fun loadById(id: AuthorId): Author {
        return Author(
            AuthorId(UUID.randomUUID().toString()),
            "ANONYMOUS"
        )
    }
}
