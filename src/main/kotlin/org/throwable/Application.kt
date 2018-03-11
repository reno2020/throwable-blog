package org.throwable

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/10 9:03
 * @description
 */
@SpringBootApplication
class Application

fun main(vararg args: String) {
    runApplication<Application>(* args)
}

