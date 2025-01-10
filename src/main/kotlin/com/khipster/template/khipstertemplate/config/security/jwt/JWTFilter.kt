package com.khipster.template.khipstertemplate.config.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

/**
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is
 * found.
 */
@Component
class JWTFilter(private val tokenProvider: TokenProvider) : OncePerRequestFilter() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        servletRequest: HttpServletRequest,
        servletResponse: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = resolveToken(servletRequest)
        println("jwt $jwt")
        if (jwt != null && jwt.isNotBlank() && this.tokenProvider.validateToken(jwt)) {
            println("go 2")
            val authentication = this.tokenProvider.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
            println("name ${authentication.name}")
            filterChain.doFilter(servletRequest, servletResponse)
            return
        }
        if (jwt.isNullOrBlank() || !jwt.startsWith("Bearer ")) {
            filterChain.doFilter(servletRequest, servletResponse)
            return
        }


    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        if (!bearerToken.isNullOrBlank() && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }
        val jwt = request.getParameter(AUTHORIZATION_TOKEN)
        if (!jwt.isNullOrBlank()) {
            return jwt
        }
        return null
    }

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"

        const val AUTHORIZATION_TOKEN: String = "access_token"
    }
}
