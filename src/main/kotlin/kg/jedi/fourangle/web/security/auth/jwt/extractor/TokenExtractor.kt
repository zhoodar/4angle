package kg.jedi.fourangle.web.security.auth.jwt.extractor

interface TokenExtractor {
    fun extract(payload: String): String
}
