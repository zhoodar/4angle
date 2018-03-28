package kg.jedi.fourangle.web.controller.dto

import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class AccountDto(

        @NotBlank(message = "Name can not be null or empty")
        var name: String,

        @Email(message = "Email should be valid")
        var email: String,

        var phoneNumber: String?,

        @NotBlank
        @Min(value = 6, message = "Password must not be less than 6")
        var password: String
)