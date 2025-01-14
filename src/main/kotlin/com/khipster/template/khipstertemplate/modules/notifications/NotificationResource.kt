package com.khipster.template.khipstertemplate.modules.notifications

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class NotificationResource(private val firebaseMsgService: FirebaseMessagingService) {

     @PostMapping("/notifications")
     fun sendNotification(@RequestBody notificationMessage: NotificationMessage) {
         firebaseMsgService.sendNotificationToAllDevices(notificationMessage)
     }

}