package wiki.neoul.api.commons.resolvers

import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import wiki.neoul.api.authentication.adapter.inbound.external.RequesterPayload
import wiki.neoul.api.authentication.application.service.SecurityContextHolder
import wiki.neoul.api.commons.UserDataPayload
import wiki.neoul.api.document.adapter.inbound.external.AuthorPayload

@Component
class UserDataPayloadResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val type = parameter.parameterType
        return type.isAssignableFrom(AuthorPayload::class.java) || type.isAssignableFrom(RequesterPayload::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): UserDataPayload? {
        val maybeContext = SecurityContextHolder.getContext()
        return maybeContext?.let {
            UserDataPayload(
                it.displayName,
                it.userId
            )
        }
    }
}
