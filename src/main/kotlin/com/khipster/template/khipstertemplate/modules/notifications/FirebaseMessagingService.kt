package com.khipster.template.khipstertemplate.modules.notifications

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Service

@Service
class FirebaseMessagingService(private val firebaseMessaging: FirebaseMessaging) {

    // send message to specific device
    fun sendNotificationToSpecificDevice(notificationMessage: NotificationMessage) {

        val notification = Notification
            .builder()
            .setTitle(notificationMessage.title)
            .setBody(notificationMessage.body)
            .setImage(notificationMessage.image)
            .build()

        val message = Message.builder()
            .setToken(notificationMessage.recipient)
            .setNotification(notification)
            .putAllData(notificationMessage.data)
            .build()

        runCatching { firebaseMessaging.send(message) }
            .onFailure { println("Error sending notification: ${it.message}") }
            .onSuccess { println("Notification sent successfully") }
    }

    // send message to all devices
    fun sendNotificationToAllDevices(notificationMessage: NotificationMessage) {

        val notification = Notification
            .builder()
            .setTitle(notificationMessage.title)
            .setBody(notificationMessage.body)
            .setImage(notificationMessage.image)
            .build()

        val message = Message.builder()
            .setNotification(notification)
            .putAllData(notificationMessage.data)
            .setTopic("all")
            .build()

        runCatching { firebaseMessaging.send(message) }
            .onFailure { println("Error sending notification: ${it.message}") }
            .onSuccess { println("Notification sent successfully") }
    }

}