package kg.jedi.fourangle.web.security.auth.jwt.extractor

import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils

@Component
class JwtHeaderTokenExtractor : TokenExtractor {

    override fun extract(payload: String): String {
        if (StringUtils.isEmpty(payload)) {
            throw AuthenticationServiceException("Authorization header cannot be blank!")
        }

        if (payload.length < HEADER_PREFIX.length) {
            throw AuthenticationServiceException("Invalid authorization header size.")
        }

        return payload.substring(HEADER_PREFIX.length, payload.length)
    }

    companion object {
        var HEADER_PREFIX = "Bearer "
    }
}
