package com.khipster.template.khipstertemplate.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestTemplate
import java.util.*

@Configuration
class RestClientConfiguration {

    @Value("\${third-party.vms-api.url}")
    private lateinit var thirdPartyUrl: String

    @Value("\${third-party.luna-api.api}")
    private lateinit var visionLabsUrl: String

    @Value("\${third-party.luna-api.username}")
    private lateinit var username: String

    @Value("\${third-party.luna-api.password}")
    private lateinit var password: String

    @Bean
    fun restClient(): RestClient {
        return RestClient.builder()
            .baseUrl(thirdPartyUrl)
            .build()
    }

    @Bean
    @Qualifier("restClientCommon")
    fun restClientCommon(): RestClient {
        return RestClient.builder()
            .build()
    }

    @Bean
    @Qualifier("visionLabsClient")
    fun visionLabsClient(): RestClient {
        val auth = "$username:$password"
        val encodedAuth = Base64.getEncoder().encodeToString(auth.toByteArray())

        val objectMapper = ObjectMapper().apply {
            propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
        }
        return RestClient.builder()
            .baseUrl("$visionLabsUrl/api")
            .defaultHeaders { it.setBasicAuth(encodedAuth) }
            .messageConverters {
                it.add(MappingJackson2HttpMessageConverter(objectMapper))
            }
            .build()
    }

    @Bean
    @Qualifier("restTemplate")
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

}

