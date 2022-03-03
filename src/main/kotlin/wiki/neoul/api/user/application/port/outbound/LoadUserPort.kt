package wiki.neoul.api.user.application.port.outbound

import wiki.neoul.api.user.domain.User

interface LoadUserPort {
    fun loadById(userId: User.UserId): User?
}
