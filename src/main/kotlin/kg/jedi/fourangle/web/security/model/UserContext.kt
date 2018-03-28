package kg.jedi.fourangle.web.security.model

import org.springframework.security.core.GrantedAuthority

class UserContext(val id: Long?, val username: String, val authorities: List<GrantedAuthority>) {

    companion object {
        fun create(id: Long?, username: String, authorities: List<GrantedAuthority>): UserContext {
            if (null == id) throw IllegalArgumentException("UserId is null.")
            return UserContext(id, username, authorities)
        }
    }
}

