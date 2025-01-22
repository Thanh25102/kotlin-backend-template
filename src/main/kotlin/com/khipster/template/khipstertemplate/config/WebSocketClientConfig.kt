package com.khipster.template.khipstertemplate.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebSocketClientConfig {

    @Bean
    fun rawWebSocketClient(): RawWebSocketClient {
        return RawWebSocketClient().apply {
            autoStart(
                "ws://localhost:8765",
                mapOf("Authorization" to "Bearer thanh")
            )
        }
    }
}

