package wiki.neoul.api.user.adapter.outbound.persistence

import wiki.neoul.api.commons.annotations.PersistenceAdapter
import wiki.neoul.api.user.application.port.outbound.LoadUserPort
import wiki.neoul.api.user.domain.User

@PersistenceAdapter
class UserPersistenceAdapter(
    private val repository: UserJpaRepository
) : LoadUserPort {
    override fun loadById(userId: User.UserId): User? {
        val userEntity = repository.findById(userId.value)
        return userEntity.orElse(null)?.let {
            return when (it.userType) {
                UserEntity.UserType.NORMAL_USER -> {
                    User.NormalUser.from(id = it.id, email = it.email, name = it.name)
                }
                UserEntity.UserType.ANONYMOUS -> {
                    User.Anonymous.from(it.id)
                }
            }
        }
    }
}
