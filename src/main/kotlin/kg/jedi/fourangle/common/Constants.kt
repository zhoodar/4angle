package kg.jedi.fourangle.common

object Endpoints {
    private const val API = "/api"
    private const val V1 = "/v1"

    const val API_V1 = API + V1
    const val AUTH_V1 = "$API_V1/auth"
    const val ADVERT_V1 = "$API_V1/adverts"


    const val SECURE_API_V1 = "$API_V1/secure"
    const val ADVERT_SECURE_V1 = "$SECURE_API_V1/adverts"
    const val ACCOUNT_SECURE_V1 = "$SECURE_API_V1/accounts"
}