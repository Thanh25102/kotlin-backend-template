package com.khipster.template.khipstertemplate.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class WebSocketClientConfig {

    // get properties from application.yml

    @Value("\${third-party.luna-api.username}")
    private lateinit var username: String

    @Value("\${third-party.luna-api.password}")
    private lateinit var password: String

    @Value("\${third-party.luna-api.ws}")
    private lateinit var websocketUrl: String


    @Bean
    fun rawWebSocketClient(objectMapper: ObjectMapper): RawWebSocketClient {
        return RawWebSocketClient(objectMapper).apply {
            val auth = "$username:$password"
            val encodedAuth = Base64.getEncoder().encodeToString(auth.toByteArray())
            autoStart(
                websocketUrl,
                mapOf("Authorization" to "Basic $encodedAuth")
            )
        }
    }
}

