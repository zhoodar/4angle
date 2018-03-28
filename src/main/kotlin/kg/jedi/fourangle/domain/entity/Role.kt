package kg.jedi.fourangle.domain.entity

import javax.persistence.*

@Entity
@Table(name = "roles")
class Role : BaseEntity() {

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    var name: RoleName = RoleName.BASIC
}

enum class RoleName {
    BASIC, ANONYMOUS, ADMIN
}