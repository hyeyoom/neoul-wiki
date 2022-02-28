package wiki.neoul.api.document.application.port.inbound

import java.time.LocalDateTime

interface ReadDocumentUseCase {

    fun execute(command: ReadDocumentQuery): ReadDocumentQueryResult

    data class ReadDocumentQuery(val documentId: String)

    data class ReadDocumentQueryResult(
        val title: String,
        val content: String,
        val authorName: String,
        val createdDate: LocalDateTime,
        val lastModifiedDate: LocalDateTime,
    )

    sealed class ReadDocumentException(message: String) : RuntimeException(message) {
        class DocumentNotFoundException(id: String) : ReadDocumentException(
            "Cannot found document: $id"
        )
    }
}
