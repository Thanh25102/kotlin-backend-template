package com.khipster.template.khipstertemplate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableAsync
class KhipsterTemplateApplication

fun main(args: Array<String>) {
    println("email.activation.greeting")
    runApplication<KhipsterTemplateApplication>(*args)
}
