package kg.jedi.fourangle.web.security.token

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import kg.jedi.fourangle.web.security.config.JwtProperty
import kg.jedi.fourangle.web.security.model.AccessJwtToken
import kg.jedi.fourangle.web.security.model.UserContext
import org.springframework.stereotype.Component


@Component
class DefaultJwtTokenFactory(private val settings: JwtProperty) : IJwtTokenFactory {

    override fun createAccessJwtToken(userContext: UserContext): AccessJwtToken {
        if (null == userContext.id)
            throw IllegalArgumentException("Cannot create JWT Token without Id")

        if (userContext.authorities.isEmpty())
            throw IllegalArgumentException("User doesn't have any privileges")

        val claims = Jwts.claims().setSubject(userContext.username)
        claims.id = userContext.id.toString()
        claims.put("scopes", userContext.authorities.map { s -> s.toString() })

        val token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.issuer)
                .signWith(SignatureAlgorithm.HS512, settings.signingKey)
                .compact()

        return AccessJwtToken(token)
    }
}
