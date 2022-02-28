package wiki.neoul.api.document.application.service

import wiki.neoul.api.commons.annotations.UseCase
import wiki.neoul.api.document.application.port.inbound.WriteDocumentUseCase
import wiki.neoul.api.document.application.port.outbound.SaveDocumentPort
import wiki.neoul.api.document.domain.AuthorId
import wiki.neoul.api.document.domain.WikiDocument
import java.time.LocalDateTime

@UseCase
class WriteDocumentService(
    private val saveDocumentPort: SaveDocumentPort,
) : WriteDocumentUseCase {

    override fun execute(command: WriteDocumentUseCase.WriteDocumentCommand): WriteDocumentUseCase.WriteDocumentResult {
        val newDocument = WikiDocument.createNewDocument(
            title = command.title,
            content = command.content,
            now = LocalDateTime.now(),
            authorId = AuthorId(id = command.authorId),
        )
        val savedDocument = saveDocumentPort.save(newDocument)
        return WriteDocumentUseCase.WriteDocumentResult(
            savedDocument.value
        )
    }
}
