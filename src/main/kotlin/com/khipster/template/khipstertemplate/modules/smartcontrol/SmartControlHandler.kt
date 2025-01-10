package com.khipster.template.khipstertemplate.modules.smartcontrol

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Service
import java.security.Principal
import org.springframework.messaging.simp.SimpMessagingTemplate

@Service
class SmartControlHandler(private val messagingTemplate : SimpMessagingTemplate) {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    fun processMessage(message: String): String {
        println("Received message from client: $message")
        return "Processed message: $message"
    }

    @MessageMapping("/private-message")
    fun processPrivateMessage(message: String, principal: Principal) {
        println("Private message from ${principal.name}: $message")
    }

    fun sendPublicMessage(message: String) {
        println("Sending public message: $message")
        messagingTemplate.convertAndSend("/topic/messages", message)
    }

    fun sendPrivateMessage(username: String, message: String) {
        messagingTemplate.convertAndSendToUser(username, "/queue/private", message)
    }

}