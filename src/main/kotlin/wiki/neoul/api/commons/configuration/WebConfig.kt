package wiki.neoul.api.commons.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import wiki.neoul.api.authentication.adapter.inbound.AuthenticationInterceptor
import wiki.neoul.api.commons.resolvers.UserDataPayloadResolver

@Configuration
class WebConfig(
    private val authenticationInterceptor: AuthenticationInterceptor,
    private val userDataPayloadResolver: UserDataPayloadResolver,
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authenticationInterceptor)
        super.addInterceptors(registry)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userDataPayloadResolver)
        super.addArgumentResolvers(resolvers)
    }
}
