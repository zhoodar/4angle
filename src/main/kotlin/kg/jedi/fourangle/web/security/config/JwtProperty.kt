package kg.jedi.fourangle.web.security.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotNull


@Validated
@Component
@ConfigurationProperties(prefix = "jwt.token")
class JwtProperty {

    @NotNull
    lateinit var issuer: String

    @NotNull
    lateinit var signingKey: String
}
