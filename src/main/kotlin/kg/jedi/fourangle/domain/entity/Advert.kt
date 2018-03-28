package kg.jedi.fourangle.domain.entity

import javax.persistence.*

@Entity
@Table(name = "adverts")
class Advert() : BaseEntity() {

    @Column(name = "tittle")
    var tittle: String? = null

    @Column(name = "description", length = 500)
    var description: String? = null

    @Column(name = "contacts")
    var contacts: String? = null

    @Column(name = "price")
    var price: String? = null

    @Column(name = "region")
    var region: String? = null

    @Column(name = "address")
    var address: String? = null

    @ManyToMany
    @JoinTable(name = "advert_image",
            joinColumns = [JoinColumn(name = "advert_id")],
            inverseJoinColumns = [(JoinColumn(name = "image_id"))])
    var images: MutableList<Image> = mutableListOf()
}