package wiki.neoul.api.user.application.service

import wiki.neoul.api.commons.annotations.UseCase
import wiki.neoul.api.user.application.port.inbound.SearchUserUseCase
import wiki.neoul.api.user.application.port.outbound.LoadUserPort
import wiki.neoul.api.user.domain.User

@UseCase
class SearchUserQueryService(
    private val loadUserPort: LoadUserPort,
) : SearchUserUseCase {
    override fun execute(query: SearchUserUseCase.SearchUserQuery): SearchUserUseCase.SearchUserQueryResult {
        val user = loadUserPort
            .loadById(User.UserId(query.userId))
            ?: throw SearchUserUseCase.SearchUserUseCaseException.UserNotFoundException(query.userId)

        return SearchUserUseCase.SearchUserQueryResult(
            userName = user.name,
            userEmail = user.email
        )
    }
}
