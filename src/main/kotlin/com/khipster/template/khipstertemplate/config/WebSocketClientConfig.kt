package com.khipster.template.khipstertemplate.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Base64

@Configuration
class WebSocketClientConfig {

    @Bean
    fun rawWebSocketClient(objectMapper: ObjectMapper): RawWebSocketClient {
        return RawWebSocketClient(objectMapper).apply {
            val username = "vlabs@vlabs.vlabs"
            val password = "vlabs"
            val auth = "$username:$password"
            val encodedAuth = Base64.getEncoder().encodeToString(auth.toByteArray())
            autoStart(
                "ws://10.6.18.2:8080/api/lp5/6/ws",
                mapOf("Authorization" to "Basic $encodedAuth")
            )
        }
    }
}

