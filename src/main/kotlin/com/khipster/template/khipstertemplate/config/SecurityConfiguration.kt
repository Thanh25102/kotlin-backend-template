package com.khipster.template.khipstertemplate.config

import com.khipster.template.khipstertemplate.config.security.ADMIN
import com.khipster.template.khipstertemplate.config.security.jwt.JWTConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.filter.CorsFilter
import tech.jhipster.config.JHipsterProperties

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfiguration(
    private val jHipsterProperties: JHipsterProperties,
    private val corsFilter: CorsFilter,
) {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity, jwtConfigurer: JWTConfigurer): SecurityFilterChain {
        http
            .csrf { csrf ->
                csrf
                    .ignoringRequestMatchers(AntPathRequestMatcher("/h2-console/**"))
                    .disable()
            }
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter::class.java)
            .headers { headers ->
                headers
                    .contentSecurityPolicy { csp ->
                        csp.policyDirectives(jHipsterProperties.security.contentSecurityPolicy)
                    }
                    .referrerPolicy { referrer ->
                        referrer.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
                    }
                    .permissionsPolicyHeader { permissions ->
                        permissions.policy("camera=(), fullscreen=(self), geolocation=(), gyroscope=(), magnetometer=(), microphone=(), midi=(), payment=(), sync-xhr=()")
                    }
            }
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers("/i18n/**").permitAll()
                    .requestMatchers("/content/**").permitAll()
                    .requestMatchers("/swagger-ui/**").permitAll()
                    // swagger resources
                    .requestMatchers("/swagger-ui/index.html").permitAll()
                    .requestMatchers("/swagger-ui/**").permitAll()
                    .requestMatchers("/v3/api-docs/**").permitAll()
                    .requestMatchers("/api-docs").permitAll()
                    .requestMatchers("/api-docs/**").permitAll()
                    .requestMatchers("/swagger-ui-custom.html").permitAll()
                    .requestMatchers("/api-docs/swagger-config").permitAll()
                    .requestMatchers("/v3/api-docs/swagger-config").permitAll()
                    .requestMatchers("/test/**").permitAll()
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers("/api/authenticate").permitAll()
                    .requestMatchers("/api/register").permitAll()
                    .requestMatchers("/api/activate").permitAll()
                    .requestMatchers("/api/account/reset-password/init").permitAll()
                    .requestMatchers("/api/account/reset-password/finish").permitAll()
                    .requestMatchers("/api/admin/**").hasAuthority(ADMIN)
                    .requestMatchers("/api/**").authenticated()
                    .requestMatchers("/websocket/**").authenticated()
                    .requestMatchers("/management/health").permitAll()
                    .requestMatchers("/management/health/**").permitAll()
                    .requestMatchers("/management/info").permitAll()
                    .requestMatchers("/management/prometheus").permitAll()
                    .requestMatchers("/swagger-ui-custom.html").permitAll()
                    .requestMatchers("/management/**").hasAuthority(ADMIN)
                    .anyRequest()
                    .authenticated()
            }
            .formLogin { it.disable() }
            .with(jwtConfigurer) { }

        return http.build()
    }

}
