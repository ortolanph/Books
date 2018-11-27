package org.books

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.util.logging.Logger

@SpringBootApplication
@EnableAutoConfiguration
class Application

    private val LOGGER = Logger.getLogger(Application::class.java.name)

    fun main(args: Array<String>) {
        LOGGER.fine("Running App")
        val ctx = SpringApplication.run(Application::class.java, *args)
    }

