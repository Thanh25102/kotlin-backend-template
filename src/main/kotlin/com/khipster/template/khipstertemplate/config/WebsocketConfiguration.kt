package com.khipster.template.khipstertemplate.config

import com.khipster.template.khipstertemplate.config.security.ANONYMOUS
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.web.socket.server.HandshakeInterceptor
import org.springframework.web.socket.server.support.DefaultHandshakeHandler
import tech.jhipster.config.JHipsterProperties
import java.security.Principal
import java.util.*
import kotlin.Throws

@Configuration
@EnableWebSocketMessageBroker
class WebsocketConfiguration(
    private val jHipsterProperties: JHipsterProperties
) : WebSocketMessageBrokerConfigurer {

    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/topic")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        val allowedOrigins =
            Optional.ofNullable(jHipsterProperties.cors.allowedOrigins)
                .map { origins -> origins.toTypedArray() }
                .orElse(arrayOfNulls(0))

        registry.addEndpoint("/websocket/tracker")
            .setHandshakeHandler(defaultHandshakeHandler())
            .setAllowedOrigins(*allowedOrigins)
    }

    private fun defaultHandshakeHandler(): DefaultHandshakeHandler {
        return object : DefaultHandshakeHandler() {
            override fun determineUser(
                request: ServerHttpRequest,
                wsHandler: WebSocketHandler,
                attributes: Map<String?, Any?>
            ): Principal? {
                var principal: Principal? = request.principal
                println("principal: $principal")
                if (principal == null) {
                    val authorities = mutableListOf<SimpleGrantedAuthority>()
                    authorities.add(SimpleGrantedAuthority(ANONYMOUS))
                    principal = AnonymousAuthenticationToken("WebsocketConfiguration", "anonymous", authorities)
                }
                return principal
            }
        }
    }

}
