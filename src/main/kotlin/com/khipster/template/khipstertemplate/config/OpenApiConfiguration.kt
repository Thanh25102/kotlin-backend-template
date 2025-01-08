package com.khipster.template.khipstertemplate.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.servers.Server
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        contact = Contact(
            name = "Bùi Mạnh Thành",
            email = "manhthanh147@gmail.com"
        ),
        title = "Face detection API",
        version = "v1",
        description = "Face detection API",
    ),
    security = [SecurityRequirement(name = "authorization")],
)
@SecurityScheme(
    name = "authorization",
    description = "JWT Token auth",
    scheme = "Bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    `in` = SecuritySchemeIn.HEADER,
)
class OpenApiConfiguration {
    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("spring-public")
            .pathsToMatch("/**")
            .build();
    }

    @Bean
    fun adminApi():GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("spring-admin")
            .pathsToMatch("/**")
            .build()
    }
}