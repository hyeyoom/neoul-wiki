package wiki.neoul.api.authentication.application.service

data class SecurityContext(
    val userId: String,
    val displayName: String,
)

object SecurityContextHolder {
    private val holder: ThreadLocal<SecurityContext> = ThreadLocal()

    fun setContext(context: SecurityContext) {
        holder.set(context)
    }

    fun getContext(): SecurityContext? {
        return holder.get()
    }
}
