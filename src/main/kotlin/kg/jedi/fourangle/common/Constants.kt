package kg.jedi.fourangle.common

object Endpoints {
    private const val API = "/api"
    private const val V1 = "/v1"

    const val API_V1 = API + V1
    const val SECURE_API_V1 = "$API_V1/secure"
    const val ACCOUNT_V1 = "$API_V1/accounts"
    const val AUTH_V1 = "$API_V1/auth"
}