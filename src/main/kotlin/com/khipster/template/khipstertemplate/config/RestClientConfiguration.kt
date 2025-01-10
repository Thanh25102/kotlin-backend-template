package com.khipster.template.khipstertemplate.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestTemplate

@Configuration
class RestClientConfiguration {

    @Value("\${third-party.vms-api.url}")
    private lateinit var thirdPartyUrl: String

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
    @Qualifier("restTemplate")
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

}

