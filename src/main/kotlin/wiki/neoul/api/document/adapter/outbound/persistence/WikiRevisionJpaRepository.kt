package wiki.neoul.api.document.adapter.outbound.persistence

import org.springframework.data.jpa.repository.JpaRepository
import wiki.neoul.api.document.adapter.outbound.persistence.entity.WikiRevisionEntity

interface WikiRevisionJpaRepository : JpaRepository<WikiRevisionEntity, String>
