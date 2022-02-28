package wiki.neoul.api.authentication.adapter.inbound

import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import wiki.neoul.api.authentication.application.port.inbound.FirstVisitedAnonymousUseCase
import wiki.neoul.api.authentication.application.port.inbound.SearchAuthenticationUseCase
import wiki.neoul.api.authentication.application.service.SecurityContext
import wiki.neoul.api.authentication.application.service.SecurityContextHolder
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationInterceptor(
    private val searchAuthenticationUseCase: SearchAuthenticationUseCase,
    private val firstVisitedAnonymousUseCase: FirstVisitedAnonymousUseCase,
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // 현재 인증정보는 없으므로 Unauthenticated가 기본으로 들어간다.
        val ipAddress = request
            .getHeader("X-FORWARDED-FOR")
            ?: request.remoteAddr

        val result = searchAuthenticationUseCase.execute(
            SearchAuthenticationUseCase.SearchAuthenticationQuery(null, ipAddress)
        )

        val context = when (result) {
            is SearchAuthenticationUseCase.SearchAuthenticationResult.AnonymousUser -> {
                SecurityContext(result.userId, result.displayName)
            }
            is SearchAuthenticationUseCase.SearchAuthenticationResult.IdentifiedUser -> {
                SecurityContext(result.userId, result.displayName)
            }
            is SearchAuthenticationUseCase.SearchAuthenticationResult.UnidentifiedUser -> {
                val anonymousUserResult = firstVisitedAnonymousUseCase.execute(
                    FirstVisitedAnonymousUseCase.FirstVisitedAnonymousUserProcessCommand(
                        ipAddress
                    )
                )
                SecurityContext(anonymousUserResult.userId, anonymousUserResult.userName)
            }
        }
        SecurityContextHolder.setContext(context)
        return super.preHandle(request, response, handler)
    }
}
