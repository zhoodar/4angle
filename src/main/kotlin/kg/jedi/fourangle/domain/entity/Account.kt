package kg.jedi.fourangle.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "accounts")
class Account: BaseEntity() {

    @Column(name= "username")
    var username: String? = null

    @Column(name= "password")
    var password: String? = null

}