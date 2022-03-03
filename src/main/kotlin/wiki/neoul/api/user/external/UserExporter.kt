package wiki.neoul.api.user.external

import wiki.neoul.api.authentication.adapter.outbound.remote.RequesterExternalRepository
import wiki.neoul.api.authentication.domain.Requester
import wiki.neoul.api.commons.annotations.External
import wiki.neoul.api.document.adapter.outbound.remote.AuthorRepository
import wiki.neoul.api.document.domain.Author
import wiki.neoul.api.document.domain.AuthorId
import wiki.neoul.api.user.adapter.outbound.persistence.UserEntity
import wiki.neoul.api.user.adapter.outbound.persistence.UserJpaRepository

@External
class UserExporter(
    private val userJpaRepository: UserJpaRepository,
) : RequesterExternalRepository, AuthorRepository {

    override fun saveAnonymousRequester(ipAddress: String): Requester {
        val anonymous =
            UserEntity(ipAddress, ipAddress, ipAddress, userType = UserEntity.UserType.ANONYMOUS)
        val savedEntity = userJpaRepository.save(anonymous)
        return Requester(
            id = Requester.RequesterId(savedEntity.id),
            displayName = savedEntity.name
        )
    }

    override fun loadByRequesterId(id: Requester.RequesterId): Requester? {
        return userJpaRepository
            .findById(id.value)
            .orElse(null)
            .let {
                Requester(
                    id = Requester.RequesterId(it.id),
                    displayName = it.name
                )
            }
    }

    override fun loadById(id: AuthorId): Author? {
        return userJpaRepository
            .findById(id.value)
            .orElse(null)
            .let {
                Author(
                    id = AuthorId(it.id),
                    name = it.name
                )
            }
    }
}
