package com.khipster.template.khipstertemplate.config.security.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.util.ObjectUtils
import tech.jhipster.config.JHipsterProperties
import java.nio.charset.StandardCharsets
import java.security.Key
import java.security.SignatureException
import java.util.*

private const val AUTHORITIES_KEY = "auth"

private const val INVALID_JWT_TOKEN = "Invalid JWT token."

@Component
class TokenProvider(
    private val jHipsterProperties: JHipsterProperties
) {

    private val log = LoggerFactory.getLogger(javaClass)

    private val key: Key = run {
        val base64Secret = jHipsterProperties.security.authentication.jwt.base64Secret

        val keyBytes: ByteArray = if (!ObjectUtils.isEmpty(base64Secret)) {
            log.debug("Using a Base64-encoded JWT secret key")
            Decoders.BASE64.decode(base64Secret)
        } else {
            log.warn(
                "Warning: the JWT key used is not Base64-encoded. " +
                        "We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security."
            )
            val secret = jHipsterProperties.security.authentication.jwt.secret
            println("secret is $secret")
            secret.toByteArray(StandardCharsets.UTF_8)
        }

        Keys.hmacShaKeyFor(keyBytes)
    }

    private val jwtParser: JwtParser = Jwts.parserBuilder().setSigningKey(key).build()

    private val tokenValidityInMilliseconds: Long =
        1000 * jHipsterProperties.security.authentication.jwt.tokenValidityInSeconds

    private val tokenValidityInMillisecondsForRememberMe: Long =
        1000 * jHipsterProperties.security.authentication.jwt.tokenValidityInSecondsForRememberMe

    fun createToken(authentication: Authentication, rememberMe: Boolean): String {
        val authorities = authentication.authorities.asSequence()
            .map { it.authority }
            .joinToString(separator = ",")

        val now = Date().time
        val validity = if (rememberMe) {
            Date(now + this.tokenValidityInMillisecondsForRememberMe)
        } else {
            Date(now + this.tokenValidityInMilliseconds)
        }

        return Jwts.builder()
            .setSubject(authentication.name)
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val claims = jwtParser.parseClaimsJws(token)?.body

        val authorities = claims?.get(AUTHORITIES_KEY)?.toString()
            ?.splitToSequence(",")
            ?.filter { it.trim().isNotEmpty() }
            ?.mapTo(mutableListOf()) { SimpleGrantedAuthority(it) }

        val principal = User(claims?.subject, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    fun validateToken(authToken: String): Boolean {
        try {
            jwtParser.parseClaimsJws(authToken)
            return true
        } catch (e: ExpiredJwtException) {
            log.trace(INVALID_JWT_TOKEN, e)
        } catch (e: UnsupportedJwtException) {
            log.trace(INVALID_JWT_TOKEN, e)
        } catch (e: MalformedJwtException) {
            log.trace(INVALID_JWT_TOKEN, e)
        } catch (e: SignatureException) {
            log.trace(INVALID_JWT_TOKEN, e)
        } catch (e: IllegalArgumentException) {
            log.error("Token validation error {}", e.message)
        }
        return false
    }
}