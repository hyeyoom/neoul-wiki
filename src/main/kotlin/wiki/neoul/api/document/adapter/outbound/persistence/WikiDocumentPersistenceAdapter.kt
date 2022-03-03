package wiki.neoul.api.document.adapter.outbound.persistence

import wiki.neoul.api.commons.annotations.PersistenceAdapter
import wiki.neoul.api.document.adapter.outbound.persistence.entity.WikiDocumentEntity
import wiki.neoul.api.document.adapter.outbound.persistence.entity.WikiRevisionEntity
import wiki.neoul.api.document.application.port.outbound.LoadDocumentPort
import wiki.neoul.api.document.application.port.outbound.SaveDocumentPort
import wiki.neoul.api.document.domain.AuthorId
import wiki.neoul.api.document.domain.WikiDocument
import javax.transaction.Transactional

@PersistenceAdapter
class WikiDocumentPersistenceAdapter(
    private val documentRepository: WikiDocumentJpaRepository,
    private val revisionRepository: WikiRevisionJpaRepository,
) : SaveDocumentPort, LoadDocumentPort {

    override fun findById(id: WikiDocument.WikiDocumentId): WikiDocument? {
        val maybeDocument = documentRepository.findByDocumentId(id.value).first()
        return maybeDocument
            ?.let {
                val revision = it.revisions.first()
                return WikiDocument(
                    id = WikiDocument.WikiDocumentId(it.id!!),
                    title = it.title,
                    history = listOf(
                        WikiDocument.WikiRevision(
                            revisionId = WikiDocument.WikiRevision.WikiRevisionId(revision.id!!),
                            documentId = WikiDocument.WikiDocumentId(revision.document.id!!),
                            authorId = AuthorId(revision.authorId),
                            content = revision.content,
                            createdDate = revision.createdDate,
                        )
                    ),
                    createdDate = it.createdDate
                )
            }
    }

    @Transactional
    override fun save(wikiDocument: WikiDocument): WikiDocument.WikiDocumentId {
        val document = wikiDocument.toWikiDocumentEntity()
        val savedEntity = documentRepository.save(document)
        val revision = wikiDocument.toWikiRevisionEntity(savedEntity)
        document.addRevision(revision)
        revisionRepository.save(revision)
        return WikiDocument.WikiDocumentId(savedEntity.id!!)
    }

    private fun WikiDocument.toWikiDocumentEntity(): WikiDocumentEntity {
        return WikiDocumentEntity(
            id = id.value,
            title = title,
            createdDate = createdDate
        )
    }

    private fun WikiDocument.toWikiRevisionEntity(wikiDocumentEntity: WikiDocumentEntity): WikiRevisionEntity {
        val latestRevision = this.getLatestDocument() ?: throw error("Fatal error detected. Revision does not exist.")
        return WikiRevisionEntity(
            id = latestRevision.revisionId.value,
            document = wikiDocumentEntity,
            authorId = latestRevision.authorId.value,
            content = latestRevision.content,
            createdDate = latestRevision.createdDate
        )
    }
}
