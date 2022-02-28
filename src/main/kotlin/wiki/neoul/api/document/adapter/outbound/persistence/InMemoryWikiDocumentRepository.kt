package wiki.neoul.api.document.adapter.outbound.persistence

import org.springframework.stereotype.Repository
import wiki.neoul.api.document.domain.WikiDocument

@Repository
class InMemoryWikiDocumentRepository : WikiDocumentRepository {
    private val db = HashMap<WikiDocument.WikiDocumentId, WikiDocument>()

    override fun findByWikiDocumentId(id: WikiDocument.WikiDocumentId): WikiDocument? = db[id]

    override fun save(wikiDocument: WikiDocument): WikiDocument.WikiDocumentId {
        val id = wikiDocument.id
        db[id] = wikiDocument
        return id
    }
}
