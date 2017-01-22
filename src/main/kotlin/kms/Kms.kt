package kms

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * @author Maxim Neverov
 */
@SpringBootApplication
class Kms

fun main(args: Array<String>) {
    SpringApplication.run(Kms::class.java, *args)
}