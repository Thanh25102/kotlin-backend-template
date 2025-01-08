package com.khipster.template.khipstertemplate.modules.auth

import com.khipster.template.khipstertemplate.config.security.isPasswordLengthInValid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class AuthResource(private val authService: AuthService) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@Valid userRegister: UserRegisterDTO) {
        if (userRegister.password.isPasswordLengthInValid()) throw InvalidPasswordException()
        authService.register(userRegister)
    }

    @GetMapping("/activate")
    fun activate(@RequestParam(value = "key") key: String) {
        authService.activate(key) ?: throw AccountResourceException("No user was found for this activation key")
    }


}