package com.khipster.template.khipstertemplate.config

import com.khipster.template.khipstertemplate.config.security.ADMIN
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import org.springframework.messaging.simp.SimpMessageType
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager

@Configuration
@EnableWebSocketSecurity
class WebsocketSecurityConfiguration {

    @Bean
    fun messageAuthorizationManager(messages: MessageMatcherDelegatingAuthorizationManager.Builder): AuthorizationManager<Message<*>> {
        messages
            .simpDestMatchers("/user/**").hasRole("USER")
            .nullDestMatcher().authenticated()
            .simpDestMatchers("/topic/tracker").hasAuthority(ADMIN)
            .simpDestMatchers("/topic/**").authenticated()
            .simpTypeMatchers(SimpMessageType.MESSAGE, SimpMessageType.SUBSCRIBE).denyAll()
            .anyMessage().denyAll()

        return messages.build()
    }

}
