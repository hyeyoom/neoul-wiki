package wiki.neoul.api.document.application.port.outbound

import wiki.neoul.api.document.domain.WikiDocument

interface LoadDocumentPort {
    fun findById(id: WikiDocument.WikiDocumentId): WikiDocument?
}
