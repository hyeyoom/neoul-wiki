package wiki.neoul.api.authentication.adapter.outbound.persistence.entity

import wiki.neoul.api.authentication.domain.Authentication
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(
    name = "authentication"
)
class AuthenticationEntity(

    @Id
    val id: String,

    @Column(nullable = false)
    val requesterId: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val authenticationType: Authentication.AuthenticationType,
)
