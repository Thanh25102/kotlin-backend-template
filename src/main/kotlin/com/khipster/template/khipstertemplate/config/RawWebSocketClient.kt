package com.khipster.template.khipstertemplate.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.khipster.template.khipstertemplate.modules.faces.LunaFaceDetectionEvent
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketHttpHeaders
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.handler.AbstractWebSocketHandler
import java.net.URI
import kotlin.concurrent.fixedRateTimer

@Component
class RawWebSocketClient(
    private val objectMapper : ObjectMapper
) {

    private var session: WebSocketSession? = null
    private var reconnectTimer: java.util.Timer? = null

    private fun connect(url: String, headers: Map<String, String> = emptyMap()) {
        val client = StandardWebSocketClient()
        val httpHeaders = WebSocketHttpHeaders().apply {
            headers.forEach { (key, value) -> add(key, value) }
        }
        try {
            session = client.execute(WebSocketHandler(url), httpHeaders, URI(url)).get()
            println("Connected to WebSocket server: $url with headers: $headers")
        } catch (e: Exception) {
            println("Failed to connect: ${e.message}")
            scheduleReconnect(url, headers)
        }
    }

    fun sendMessage(message: String) {
        val currentSession = session
        if (currentSession != null && currentSession.isOpen) {
            try {
                currentSession.sendMessage(TextMessage(message))
                println("Message sent: $message")
            } catch (e: Exception) {
                println("Error sending message: ${e.message}")
            }
        } else {
            println("Session is not open!")
        }
    }

    fun disconnect() {
        val currentSession = session
        reconnectTimer?.cancel()
        if (currentSession != null && currentSession.isOpen) {
            try {
                currentSession.close()
                println("Disconnected from WebSocket server.")
            } catch (e: Exception) {
                println("Error disconnecting: ${e.message}")
            }
        }
    }

    fun autoStart(url: String, headers: Map<String, String> = emptyMap()) {
        connect(url, headers)
    }

    private fun scheduleReconnect(url: String, headers: Map<String, String>) {
        reconnectTimer?.cancel()
        reconnectTimer = fixedRateTimer(initialDelay = 5000, period = 5000) {
            println("Attempting to reconnect to WebSocket server: $url")
            connect(url, headers)
        }
    }

    private inner class WebSocketHandler(private val url: String) : AbstractWebSocketHandler() {

        override fun afterConnectionEstablished(session: WebSocketSession) {
            println("Connection established!")
            reconnectTimer?.cancel()
        }

        override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
            val lunaFaceDetectionEvent = objectMapper.readValue(message.payload, LunaFaceDetectionEvent::class.java)
            println("Received message: $lunaFaceDetectionEvent")
        }

        override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
            println("Transport error: ${exception.message}")
            scheduleReconnect(url, emptyMap()) // You can update this to pass headers if needed
        }
    }
}
