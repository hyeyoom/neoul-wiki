package wiki.neoul.api.document.adapter.inbound.api

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wiki.neoul.api.document.adapter.inbound.external.AuthorPayload
import wiki.neoul.api.document.application.port.inbound.ReadDocumentUseCase
import wiki.neoul.api.document.application.port.inbound.WriteDocumentUseCase
import java.time.LocalDateTime

interface WikiDocumentApi {

    fun getWikiDocument(documentId: String): GetWikiDocumentResponse

    data class GetWikiDocumentResponse(
        val title: String,
        val content: String,
        val authorName: String,
        val createdDate: LocalDateTime,
        val lastModifiedDate: LocalDateTime,
    )

    fun createWikiDocument(request: PostWikiDocumentRequest, payload: AuthorPayload): PostWikiDocumentResponse

    data class PostWikiDocumentRequest(
        val title: String,
        val content: String,
    )

    data class PostWikiDocumentResponse(
        val documentId: String,
    )
}

@RestController
@RequestMapping("/wiki")
class WikiDocumentApiController(
    private val readDocumentUseCase: ReadDocumentUseCase,
    private val writeDocumentUseCase: WriteDocumentUseCase,
) : WikiDocumentApi {

    private val logger = KotlinLogging.logger { }

    @GetMapping("/{documentId}")
    override fun getWikiDocument(
        @PathVariable documentId: String,
    ): WikiDocumentApi.GetWikiDocumentResponse {
        val result = readDocumentUseCase.execute(ReadDocumentUseCase.ReadDocumentQuery(documentId))
        return WikiDocumentApi.GetWikiDocumentResponse(
            result.title,
            result.content,
            result.authorName,
            result.createdDate,
            result.lastModifiedDate
        )
    }

    @PostMapping
    override fun createWikiDocument(
        @RequestBody request: WikiDocumentApi.PostWikiDocumentRequest,
        payload: AuthorPayload,
    ): WikiDocumentApi.PostWikiDocumentResponse {
        logger.debug { "Request on createWikiDocument: $request" }
        logger.debug { "payload on createWikiDocument: {${payload.getUserId()} | ${payload.getDisplayName()}}" }
        val result = writeDocumentUseCase.execute(
            WriteDocumentUseCase.WriteDocumentCommand(
                title = request.title,
                content = request.content,
                authorId = payload.getUserId()
            )
        )
        return WikiDocumentApi.PostWikiDocumentResponse(result.documentId)
    }
}
