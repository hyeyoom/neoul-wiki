package wiki.neoul.api.document.adapter.outbound.persistence

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import wiki.neoul.api.document.adapter.outbound.persistence.entity.WikiDocumentEntity

interface WikiDocumentJpaRepository : JpaRepository<WikiDocumentEntity, String> {

    @Query(
        "SELECT wde " +
            "FROM WikiDocumentEntity wde " +
            "JOIN FETCH wde.revisions " +
            "WHERE wde.id = :documentId",
        countQuery = "SELECT count(wde.id) FROM WikiDocumentEntity wde"
    )
    fun findByDocumentId(
        documentId: String,
        pageable: Pageable = PageRequest.of(1, 1)
    ): Page<WikiDocumentEntity?>
}
