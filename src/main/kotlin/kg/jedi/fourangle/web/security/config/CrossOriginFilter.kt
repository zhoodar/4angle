package kg.jedi.fourangle.web.security.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.constraints.NotNull

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@PropertySource("classpath:application.properties")
class CrossOriginFilter(
        @NotNull
        @Value("\${access-control-allow-origin}")
        private val accessControlAllowOrigin: String
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        response.setHeader("Access-Control-Allow-Origin", accessControlAllowOrigin)
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
        response.setHeader("Allow", "POST, PUT, GET, OPTIONS, DELETE")
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "Cache-Control, X-Authorization, Content-Type, Accept, " +
                "X-Requested-With, remember-me, Accept-Ranges, Content-Encoding, Content-Length, Content-Type, DocumentToken")
        response.setHeader("Access-Control-Expose-Headers", "Accept-Ranges, Content-Encoding, Content-Length, Content-Type")

        if (notPreflight(request)) {
            filterChain.doFilter(request, response)
        }
    }

    private fun notPreflight(request: HttpServletRequest): Boolean {
        val api = StringUtils.countOccurrencesOf(request.requestURI, "api")
        return if (api > 1) {
            false
        } else "OPTIONS" != request.method
    }

}