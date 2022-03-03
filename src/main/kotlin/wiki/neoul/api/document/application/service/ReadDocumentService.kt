package wiki.neoul.api.document.application.service

import wiki.neoul.api.commons.annotations.UseCase
import wiki.neoul.api.document.application.port.inbound.ReadDocumentUseCase
import wiki.neoul.api.document.application.port.outbound.LoadAuthorPort
import wiki.neoul.api.document.application.port.outbound.LoadDocumentPort
import wiki.neoul.api.document.domain.WikiDocument

@UseCase
class ReadDocumentService(
    private val loadDocumentPort: LoadDocumentPort,
    private val loadAuthorPort: LoadAuthorPort,
) : ReadDocumentUseCase {

    override fun execute(command: ReadDocumentUseCase.ReadDocumentQuery): ReadDocumentUseCase.ReadDocumentQueryResult {
        val document = loadDocumentPort
            .findById(WikiDocument.WikiDocumentId(command.documentId))
            ?: throw ReadDocumentUseCase.ReadDocumentException.DocumentNotFoundException(id = command.documentId)

        return document
            .getLatestDocument()
            ?.let { latestRevision ->
                val author = loadAuthorPort
                    .loadById(latestRevision.authorId)
                    ?: throw error("Fatal error detected during searching document. authorId: ${latestRevision.authorId.value}")
                ReadDocumentUseCase.ReadDocumentQueryResult(
                    title = document.title,
                    content = latestRevision.content,
                    authorName = author.name,
                    createdDate = document.createdDate,
                    lastModifiedDate = latestRevision.createdDate,
                )
            } ?: throw error("Fatal error detected on document Id: ${document.id.value}.")
    }
}
