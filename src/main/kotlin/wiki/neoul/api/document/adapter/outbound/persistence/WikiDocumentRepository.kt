package wiki.neoul.api.document.adapter.outbound.persistence

import wiki.neoul.api.document.domain.WikiDocument

interface WikiDocumentRepository {

    fun findByWikiDocumentId(id: WikiDocument.WikiDocumentId): WikiDocument?

    fun save(wikiDocument: WikiDocument): WikiDocument.WikiDocumentId
}
