package com.khipster.template.khipstertemplate.modules.auth

import com.khipster.template.khipstertemplate.config.security.isPasswordLengthInValid
import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import com.khipster.template.khipstertemplate.repository.UserRepo
import jakarta.validation.Valid
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class AuthResource(private val authService: AuthService, private val userRepo: UserRepo) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@Valid @RequestBody userRegister: UserRegisterDTO) {
        if (userRegister.password.isPasswordLengthInValid()) throw InvalidPasswordException()
        authService.register(userRegister)
    }

    @GetMapping("/activate")
    fun activate(@RequestParam(value = "key") key: String) {
        authService.activate(key) ?: throw AccountResourceException("No user was found for this activation key")
    }

    @GetMapping("/users")
    fun getUser(principal: Principal): ResponseEntity<ApiResponse<UserDTO?>> {
        val user = userRepo.findOneByLoginOrEmail(principal.name, null)?.let {
            UserDTO(it.firstName + " " + it.lastName, it.email, it.login)
        }
        return user.wrapOrNotFound("Cannot find user")
    }

}