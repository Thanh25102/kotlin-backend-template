package com.khipster.template.khipstertemplate.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*

@Configuration
class MessageSourceConfiguration : WebMvcConfigurer {

    @Bean
    fun messageSource(): MessageSource {
        return ReloadableResourceBundleMessageSource().apply {
            setBasename("classpath:i18n/messages")
            setDefaultEncoding("UTF-8")
        }
    }

    @Bean
    fun localeResolver(): LocaleResolver {
        return SessionLocaleResolver().apply {
            setDefaultLocale(Locale.ENGLISH)
            setDefaultTimeZoneFunction {
                TimeZone.getTimeZone("UTC")
            }
        }
    }

    @Bean
    fun localeChangeInterceptor(): LocaleChangeInterceptor {
        return LocaleChangeInterceptor().apply {
            paramName = "lang"
        }
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeChangeInterceptor())
    }
}