package kg.jedi.fourangle.domain.entity

import javax.persistence.*

@Entity
@Table(name = "accounts")
class Account: BaseEntity() {

    @Column(name= "name", nullable = false)
    var name: String? = null

    @Column(name= "password", nullable = false)
    var password: String? = null

    @Column(name = "email", nullable = false)
    var email: String? = null

    @Column(name = "phone_number")
    var phoneNumber: String? = null

    @ManyToOne
    @JoinColumn(name = "role_id")
    var role: Role = Role()

}