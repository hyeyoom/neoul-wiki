package wiki.neoul.api.document.application.port.inbound

/**
 * UseCase for creating a document
 */
interface WriteDocumentUseCase {

    fun execute(command: WriteDocumentCommand): WriteDocumentResult

    data class WriteDocumentCommand(
        val title: String,
        val content: String,
        val authorId: String,
    )

    data class WriteDocumentResult(val documentId: String)
}
