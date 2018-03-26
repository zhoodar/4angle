package kg.jedi.fourangle.common

object Endpoints {
    private const val API = "api/"
    private const val V1 = "v1/"

    const val API_V1 = API + V1
    const val ACCOUNT_V1 = "${API_V1}accounts/"
}

object ApiDoc {
    const val JSON_UTF8 = "application/json; charset=utf-8"
}