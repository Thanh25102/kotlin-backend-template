package com.khipster.template.khipstertemplate.modules.auth

import com.khipster.template.khipstertemplate.config.security.jwt.TokenProvider
import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class AuthenticateResource(
    private val authenticationManager: AuthenticationManager,
    private val tokenProvider: TokenProvider
) {

    @PostMapping("/authenticate")
    fun authorize(@Valid @RequestBody loginVM: LoginVM): ResponseEntity<ApiResponse<JwtToken>> {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginVM.username, loginVM.password)
        val authentication = authenticationManager.authenticate(authenticationToken)

        SecurityContextHolder.getContext().authentication = authentication
        val jwt = tokenProvider.createToken(authentication, loginVM.rememberMe)

        return JwtToken(jwt).wrapOrNotFound(
            headers = HttpHeaders().apply { setBearerAuth(jwt) }
        )
    }

    @GetMapping("/authenticate")
    fun isAuthenticated(request: HttpServletRequest): ResponseEntity<ApiResponse<String>> {
        return request.remoteUser.wrapOrNotFound()
    }
}

data class JwtToken(val idToken: String)