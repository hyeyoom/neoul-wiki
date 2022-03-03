package wiki.neoul.api.authentication.application.service

import wiki.neoul.api.authentication.application.port.inbound.FirstVisitedAnonymousUseCase
import wiki.neoul.api.authentication.application.port.outbound.SaveAnonymousUserPort
import wiki.neoul.api.authentication.application.port.outbound.SaveAuthenticationPort
import wiki.neoul.api.authentication.domain.Authentication
import wiki.neoul.api.commons.annotations.UseCase

@UseCase
class FirstVisitedAnonymousUseProcessor(
    private val saveAuthenticationPort: SaveAuthenticationPort,
    private val saveAnonymousUserPort: SaveAnonymousUserPort,
) : FirstVisitedAnonymousUseCase {

    override fun execute(command: FirstVisitedAnonymousUseCase.FirstVisitedAnonymousUserProcessCommand):
        FirstVisitedAnonymousUseCase.FirstVisitedAnonymousUserProcessResult {
            val requester = saveAnonymousUserPort.saveAnonymousUser(command.ipAddress)
            val newAnonymousUser = Authentication.Anonymous.createNewAnonymousUser(requester.id)
            saveAuthenticationPort.save(newAnonymousUser)
            return FirstVisitedAnonymousUseCase.FirstVisitedAnonymousUserProcessResult(
                userId = requester.id.value,
                userName = requester.displayName
            )
        }
}
