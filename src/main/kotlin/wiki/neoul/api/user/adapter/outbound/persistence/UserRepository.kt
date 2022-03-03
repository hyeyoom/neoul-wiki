package wiki.neoul.api.user.adapter.outbound.persistence

import wiki.neoul.api.user.domain.User

interface UserRepository {
    fun findByUserId(userId: User.UserId): User?
}
