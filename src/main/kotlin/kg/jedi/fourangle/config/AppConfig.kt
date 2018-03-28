package kg.jedi.fourangle.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
class AppBeanConfig {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun objectMapper(objectMapper: ObjectMapper): ObjectMapper {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
        objectMapper.registerModule(JavaTimeModule())
        val hibernate4Module = Hibernate4Module()
        hibernate4Module.disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION)
                .enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING)
        objectMapper.registerModule(hibernate4Module)
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        return objectMapper
    }
}