package kg.jedi.fourangle.web.security.auth.ajax

import kg.jedi.fourangle.buisness.service.AccountCrudService
import kg.jedi.fourangle.domain.entity.Account
import kg.jedi.fourangle.web.security.model.UserContext
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.util.Assert

@Component
class AjaxAuthenticationProvider(private val encoder: BCryptPasswordEncoder,
                                 private val accountService: AccountCrudService) : AuthenticationProvider {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        Assert.notNull(authentication, "No authentication data provided")

        val username = authentication.principal as String
        val password = authentication.credentials as String

        val account = authenticateCredentials(username, password)
        val authorities = listOf(SimpleGrantedAuthority(account.role.name.name))

        val userContext = UserContext.create(account.id, username, authorities)
        return UsernamePasswordAuthenticationToken(userContext, null, userContext.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }

    private fun authenticateCredentials(username: String, password: String): Account {
        val account = accountService.getByEmailOrPhone(username)
                .orElseThrow {
                    UsernameNotFoundException("User not found: $username")
                }

        if (!encoder.matches(password, account.password)) {
            throw BadCredentialsException("Authentication Failed. Invalid username or password.")
        }
        return account
    }
}
