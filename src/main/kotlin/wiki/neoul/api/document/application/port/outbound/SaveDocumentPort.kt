package wiki.neoul.api.document.application.port.outbound

import wiki.neoul.api.document.domain.WikiDocument

interface SaveDocumentPort {

    fun save(wikiDocument: WikiDocument): WikiDocument.WikiDocumentId
}
