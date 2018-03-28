package kg.jedi.fourangle.web.security.model

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import kg.jedi.fourangle.common.logger
import org.springframework.security.authentication.BadCredentialsException

class RawAccessJwtToken(override val token: String) : JwtToken {

    fun parseClaims(signingKey: String): Jws<Claims> {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token)
        } catch (ex: Exception) {
            logger.error("Invalid JWT Token ", ex)
            throw BadCredentialsException("Invalid JWT token: ", ex)
        }

    }
}
