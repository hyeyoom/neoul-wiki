package wiki.neoul.api.document.adapter.outbound.persistence

import wiki.neoul.api.commons.annotations.PersistenceAdapter
import wiki.neoul.api.document.application.port.outbound.LoadDocumentPort
import wiki.neoul.api.document.application.port.outbound.SaveDocumentPort
import wiki.neoul.api.document.domain.WikiDocument

@PersistenceAdapter
class WikiDocumentPersistenceAdapter(
    private val repository: WikiDocumentRepository
) : SaveDocumentPort, LoadDocumentPort {

    override fun findById(id: WikiDocument.WikiDocumentId): WikiDocument? = repository.findByWikiDocumentId(id)

    override fun save(wikiDocument: WikiDocument): WikiDocument.WikiDocumentId = repository.save(wikiDocument)
}
