package wiki.neoul.api.document.adapter.outbound.persistence.entity

import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(
    name = "wiki_document",
    uniqueConstraints = [
        UniqueConstraint(name = "uq_wiki_document_title", columnNames = ["title"])
    ]
)
class WikiDocumentEntity(

    @Id
    @Column(name = "document_id")
    val id: String? = null,

    @Column(nullable = false)
    val title: String,

    @OneToMany(mappedBy = "document", cascade = [CascadeType.ALL])
    val revisions: MutableList<WikiRevisionEntity> = mutableListOf(),

    @Column(nullable = false, updatable = false)
    val createdDate: LocalDateTime
) {

    fun addRevision(revision: WikiRevisionEntity) {
        revisions.add(revision)
    }
}
