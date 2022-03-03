package wiki.neoul.api.user.adapter.outbound.persistence

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(
    name = "wiki_user",
    uniqueConstraints = [
        UniqueConstraint(name = "uq_wiki_user_email", columnNames = ["email"]),
        UniqueConstraint(name = "uq_wiki_user_name", columnNames = ["name"])
    ]
)
class UserEntity(
    @Id
    @Column(name = "user_id")
    val id: String,

    @Column(nullable = false, updatable = false)
    val email: String,

    @Column(nullable = false, updatable = false)
    val name: String,

    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    val userType: UserType,
) {
    enum class UserType {
        NORMAL_USER,
        ANONYMOUS
    }
}
