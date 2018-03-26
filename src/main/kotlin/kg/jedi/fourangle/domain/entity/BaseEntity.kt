package kg.jedi.fourangle.domain.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.ZonedDateTime
import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null

    @CreatedDate
    @Column(name = "created_at")
    var createdAt: ZonedDateTime? = null

    @LastModifiedDate
    @Column(name = "changed_at")
    var changedAt: ZonedDateTime? = null
}