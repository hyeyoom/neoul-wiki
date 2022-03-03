package wiki.neoul.api.document.domain

import java.time.LocalDateTime
import java.util.UUID

/**
 * 위키 도큐먼트
 *
 * @author hyeyoom
 */
class WikiDocument(
    val id: WikiDocumentId,
    val title: String,
    private val history: List<WikiRevision>,
    val createdDate: LocalDateTime,
) {

    companion object {
        fun createNewDocument(
            title: String,
            content: String,
            now: LocalDateTime,
            authorId: AuthorId,
        ): WikiDocument {
            val documentId = WikiDocumentId(UUID.randomUUID().toString())
            val revision = WikiRevision(
                revisionId = WikiRevision.WikiRevisionId(UUID.randomUUID().toString()),
                documentId = documentId,
                authorId = authorId,
                content = content,
                createdDate = now,
            )
            return WikiDocument(
                id = documentId,
                title = title,
                history = listOf(revision),
                createdDate = now,
            )
        }
    }

    fun getLatestDocument(): WikiRevision? = history.maxByOrNull { it.createdDate }

    data class WikiDocumentId(val value: String)

    class WikiRevision(
        val revisionId: WikiRevisionId,
        val documentId: WikiDocumentId,
        val authorId: AuthorId,
        val content: String,
        val createdDate: LocalDateTime,
    ) {
        data class WikiRevisionId(val value: String)
    }
}
