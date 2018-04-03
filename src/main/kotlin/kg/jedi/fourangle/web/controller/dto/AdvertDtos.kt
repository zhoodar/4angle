package kg.jedi.fourangle.web.controller.dto

import kg.jedi.fourangle.domain.entity.Advert
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class AdvertDto(
        @Size(min = 20, message = "Tittle should not be less than 20 symbols")
        var tittle: String? = null,

        @Size(max = 500, message = "Description should not be greater than 500 symbols")
        var description: String? = null,

        @NotBlank(message = "Contact cannot be null or empty")
        var contact: String? = null,

        @NotBlank(message = "Price cannot be null or empty")
        var price: String? = null,

        @NotBlank(message = "Region cannot be null or empty")
        var region: String? = null,

        @NotBlank(message = "Address cannot be null or empty")
        var address: String? = null,

        @NotBlank(message = "Room Number cannot be null or empty")
        var rooms: Int? = null
) {
    fun toAdvertModel(): Advert {
        val dto = this

        return Advert().apply {
            tittle = dto.tittle
            description = dto.description
            contact = dto.contact
            price = dto.price
            region = dto.region
            address = dto.address
            rooms = dto.rooms
        }
    }
}
fun Advert.toAdvertDto(): AdvertDto {
    val model = this

    return AdvertDto().apply {
        tittle = model.tittle
        description = model.description
        contact = model.contact
        price = model.price
        region = model.region
        address = model.address
        rooms = model.rooms
    }
}

