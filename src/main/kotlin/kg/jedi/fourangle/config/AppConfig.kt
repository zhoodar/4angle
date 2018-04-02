package kg.jedi.fourangle.config

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
    fun javaTimeModule(): JavaTimeModule {
        return JavaTimeModule()
    }

    @Bean
    fun hibernate4Module(): Hibernate4Module {
        val hibernate4Module = Hibernate4Module()
        hibernate4Module.disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION)
                .enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING)
        return hibernate4Module
    }
}