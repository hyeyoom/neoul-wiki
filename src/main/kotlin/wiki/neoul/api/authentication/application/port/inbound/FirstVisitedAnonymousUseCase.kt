package wiki.neoul.api.authentication.application.port.inbound

interface FirstVisitedAnonymousUseCase {

    fun execute(command: FirstVisitedAnonymousUserProcessCommand): FirstVisitedAnonymousUserProcessResult

    data class FirstVisitedAnonymousUserProcessCommand(
        val ipAddress: String,
    )

    data class FirstVisitedAnonymousUserProcessResult(
        val userId: String,
        val userName: String,
    )
}
