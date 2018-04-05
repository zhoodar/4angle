package kg.jedi.fourangle.config

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
class AppBeanConfig {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun javaTimeModule(): JavaTimeModule {
        return JavaTimeModule()
    }

    @Bean
    fun hibernate5Module(): Hibernate5Module {
        val hibernate5Module = Hibernate5Module()
        hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION)
                .enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING)
        return hibernate5Module
    }
}