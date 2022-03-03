package wiki.neoul.api.document.adapter.outbound.persistence.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(
    name = "wiki_revision"
)
class WikiRevisionEntity(

    @Id
    @Column(name = "revision_id")
    val id: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    val document: WikiDocumentEntity,

    @Column(nullable = false)
    val authorId: String,

    @Column(nullable = false)
    val content: String,

    @Column(nullable = false)
    val createdDate: LocalDateTime,
)
