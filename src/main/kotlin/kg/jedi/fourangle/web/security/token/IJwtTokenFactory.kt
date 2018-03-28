package kg.jedi.fourangle.web.security.token

import kg.jedi.fourangle.web.security.model.AccessJwtToken
import kg.jedi.fourangle.web.security.model.UserContext

interface IJwtTokenFactory {

    fun createAccessJwtToken(userContext: UserContext): AccessJwtToken
}
