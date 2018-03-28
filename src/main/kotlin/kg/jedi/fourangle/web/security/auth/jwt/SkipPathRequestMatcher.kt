package kg.jedi.fourangle.web.security.auth.jwt

import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.util.Assert
import javax.servlet.http.HttpServletRequest

class SkipPathRequestMatcher(pathsToSkip: List<String>, processingPath: String) : RequestMatcher {
    private val matchers: OrRequestMatcher
    private val processingMatcher: RequestMatcher

    init {
        Assert.notNull(pathsToSkip)
        val m = pathsToSkip.map { AntPathRequestMatcher(it) }
        matchers = OrRequestMatcher(m)
        processingMatcher = AntPathRequestMatcher(processingPath)
    }

    override fun matches(request: HttpServletRequest): Boolean {
        return if (matchers.matches(request)) {
            false
        } else processingMatcher.matches(request)
    }
}
