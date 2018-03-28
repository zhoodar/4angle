package kg.jedi.fourangle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
@EntityScan("kg.jedi.fourangle.domain")
class FourAngleApp

fun main(args: Array<String>) {
    runApplication<FourAngleApp>(*args)
}
