package pro.friendlyted.ears.music.service

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class AppConfig

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(AppConfig::class.java, *args)
    }
}
