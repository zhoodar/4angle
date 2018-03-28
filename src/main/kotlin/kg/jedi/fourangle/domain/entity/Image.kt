package kg.jedi.fourangle.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "images")
class Image : BaseEntity() {

    @Column(name = "name")
    var name: String? = null

    @Column(name = "url", length = 500)
    var url: String? = null
}