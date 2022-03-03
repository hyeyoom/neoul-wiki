package wiki.neoul.api.authentication.adapter.outbound.persistence

import org.springframework.data.jpa.repository.JpaRepository
import wiki.neoul.api.authentication.adapter.outbound.persistence.entity.AuthenticationEntity

interface AuthenticationJpaRepository : JpaRepository<AuthenticationEntity, String> {

    fun findByRequesterId(requesterId: String): AuthenticationEntity?
}
