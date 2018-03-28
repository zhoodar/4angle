package kg.jedi.fourangle.web.security.auth.jwt

import kg.jedi.fourangle.web.security.auth.JwtAuthenticationToken
import kg.jedi.fourangle.web.security.config.JwtProperty
import kg.jedi.fourangle.web.security.model.RawAccessJwtToken
import kg.jedi.fourangle.web.security.model.UserContext
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationProvider(private val jwtProperty: JwtProperty) : AuthenticationProvider {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val rawAccessToken = authentication.credentials as RawAccessJwtToken

        val jwsClaims = rawAccessToken.parseClaims(jwtProperty.signingKey!!)

        val id = jwsClaims.body.id
        val username = jwsClaims.body.subject
        val scopes = jwsClaims.body.get("scopes", List::class.java)
        val authorities = scopes.map { SimpleGrantedAuthority(it.toString()) }

        val context = UserContext.create(java.lang.Long.valueOf(id), username, authorities)

        return JwtAuthenticationToken(context, context.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return JwtAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
