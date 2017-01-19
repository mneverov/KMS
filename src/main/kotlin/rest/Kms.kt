package rest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * @author Max Neverov
 * @since
 */
@SpringBootApplication
open class Kms

fun main(args: Array<String>) {
    SpringApplication.run(Kms::class.java, *args)
}