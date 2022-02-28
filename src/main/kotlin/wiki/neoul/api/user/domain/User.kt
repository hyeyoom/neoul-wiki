package wiki.neoul.api.user.domain

sealed class User(
    val userId: UserId,
    val email: String,
    val name: String,
) {
    data class UserId(val value: String)

    class NormalUser(
        userId: UserId,
        email: String,
        name: String,
    ) : User(userId, email, name)

    class Anonymous(
        userId: UserId,
    ) : User(userId, userId.value, userId.value) {
        companion object {
            fun createNew(ipAddress: String): Anonymous = Anonymous(userId = UserId(value = ipAddress))
        }
    }
}
