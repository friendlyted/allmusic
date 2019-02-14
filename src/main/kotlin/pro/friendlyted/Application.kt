package pro.friendlyted

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(
    scanBasePackages = ["pro.friendlyted.rest", "pro.friendlyted.service"]
)
open class AppConfig

object Application {
    @JvmStatic
    open fun main(args: Array<String>) {
        SpringApplication.run(AppConfig::class.java, *args)
    }
}
