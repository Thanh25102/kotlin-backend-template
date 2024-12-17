package com.khipster.template.khipstertemplate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
class KhipsterTemplateApplication

fun main(args: Array<String>) {
    runApplication<KhipsterTemplateApplication>(*args)
}
