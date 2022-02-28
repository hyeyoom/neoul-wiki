package wiki.neoul.api.document.adapter.outbound.remote

import org.springframework.stereotype.Component
import wiki.neoul.api.document.application.port.outbound.LoadAuthorPort
import wiki.neoul.api.document.domain.Author
import wiki.neoul.api.document.domain.AuthorId

@Component
class AuthorRemoteAdapter(
    private val authorRepository: AuthorRepository,
) : LoadAuthorPort {
    override fun loadById(authorId: AuthorId): Author? {
        return authorRepository.loadById(authorId)
    }
}
