package com.khipster.template.khipstertemplate.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FirebaseConfiguration {

    @Bean
    fun firebaseMessaging(): FirebaseMessaging {
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(ClassPathResource("firebase-service-account.json").inputStream))
            .build()
        val app = FirebaseApp.initializeApp(options,"khipster-template")
        return FirebaseMessaging.getInstance(app)
    }

}