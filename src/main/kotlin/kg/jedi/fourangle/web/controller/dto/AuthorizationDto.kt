package kg.jedi.fourangle.web.controller.dto

data class LoginDto(
        var username: String,
        var password: String
)

data class TokenDto(
        var apiToken: String
)