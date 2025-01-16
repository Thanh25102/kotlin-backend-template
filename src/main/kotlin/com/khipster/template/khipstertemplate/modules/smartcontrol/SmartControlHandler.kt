package com.khipster.template.khipstertemplate.modules.smartcontrol

import org.springframework.context.ApplicationListener
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import java.security.Principal
import java.time.Instant

@Service
class SmartControlHandler(private val messagingTemplate: SimpMessagingTemplate) :
    ApplicationListener<SessionDisconnectEvent> {

    @MessageMapping("/topic/activity")
    @SendTo("/topic/tracker")
    fun processMessage(
        @Payload activityDTO: ActivityDTO,
        stompHeaderAccessor: StompHeaderAccessor,
        principal: Principal
    ): ActivityDTO {
        return activityDTO.apply {
            userLogin = principal.name
            time = Instant.now()
            sessionId = stompHeaderAccessor.sessionId
            ipAddress = stompHeaderAccessor.sessionAttributes?.get("IP_ADDRESS") as String?
        }
    }

    override fun onApplicationEvent(event: SessionDisconnectEvent) {
        val activityDTO = ActivityDTO(
            sessionId = event.sessionId,
            page = "Logout",
        )
        messagingTemplate.convertAndSend("/topic/tracker", activityDTO)
    }
}

data class ActivityDTO(
    var sessionId: String? = null,
    var userLogin: String? = null,
    var ipAddress: String? = null,
    var page: String? = null,
    var time: Instant? = null,
)