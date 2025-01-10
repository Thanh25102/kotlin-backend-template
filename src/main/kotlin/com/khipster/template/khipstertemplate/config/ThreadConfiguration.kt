package com.khipster.template.khipstertemplate.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Configuration
class ThreadConfiguration : AsyncConfigurer{

    @Bean
    fun virtualThreadExecutor(): Executor {
        return Executors.newThreadPerTaskExecutor(Thread.ofVirtual().factory())
    }

    override fun getAsyncExecutor(): Executor? {
        return Executors.newThreadPerTaskExecutor(Thread.ofVirtual().factory())
    }
}