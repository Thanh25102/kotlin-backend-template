package com.khipster.template.khipstertemplate.config

import com.khipster.template.khipstertemplate.config.security.ANONYMOUS
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.web.socket.server.support.DefaultHandshakeHandler
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor
import tech.jhipster.config.JHipsterProperties
import java.security.Principal
import java.util.*

@Configuration
@EnableWebSocketMessageBroker
class WebsocketConfiguration(
    private val jHipsterProperties: JHipsterProperties
) : WebSocketMessageBrokerConfigurer {

    private val IP_ADDRESS: String = "IP_ADDRESS"
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
            .addInterceptors(httpSessionHandshakeInterceptor())
            .setAllowedOrigins(*allowedOrigins)
    }

    @Bean
    fun httpSessionHandshakeInterceptor(): HttpSessionHandshakeInterceptor {
        return object : HttpSessionHandshakeInterceptor() {
            override fun beforeHandshake(
                request: ServerHttpRequest,
                response: ServerHttpResponse,
                wsHandler: WebSocketHandler,
                attributes: MutableMap<String, Any>
            ): Boolean {
                if (request is ServletServerHttpRequest) {
                    val servletRequest = request.servletRequest
                    attributes[StompHeaderAccessor.USER_HEADER] = servletRequest.userPrincipal
                    attributes[IP_ADDRESS] = servletRequest.remoteAddr
                }
                println("beforeHandshake")
                return true
            }

            override fun afterHandshake(
                request: ServerHttpRequest,
                response: ServerHttpResponse,
                wsHandler: WebSocketHandler,
                exception: Exception?
            ) {
                println("afterHandshake")
            }
        }
    }

    private fun defaultHandshakeHandler(): DefaultHandshakeHandler {
        return object : DefaultHandshakeHandler() {
            override fun determineUser(
                request: ServerHttpRequest,
                wsHandler: WebSocketHandler,
                attributes: Map<String?, Any?>
            ): Principal {
                var principal: Principal? = request.principal
                println("print principal $principal")
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
