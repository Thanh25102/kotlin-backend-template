package com.khipster.template.khipstertemplate.config

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfiguration {
    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("springshop-public")
            .pathsToMatch("/public/**")
            .build();
    }
    @Bean
    fun adminApi():GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("springshop-admin")
            .pathsToMatch("/admin/**")
            .build()
    }
}