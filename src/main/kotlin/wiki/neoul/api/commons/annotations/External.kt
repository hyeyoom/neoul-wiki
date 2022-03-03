package wiki.neoul.api.commons.annotations

import org.springframework.stereotype.Component

/**
 * Marking annotation for implementation which implements interface of external domain.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class External
