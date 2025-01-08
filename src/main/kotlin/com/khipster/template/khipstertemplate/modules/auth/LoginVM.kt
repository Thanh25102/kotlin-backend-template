package com.khipster.template.khipstertemplate.modules.auth

import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

data class LoginVM(
    @field:NotNull
    @field:Size(min = 1, max = 50)
    var username: String? = null,

    @field:NotNull
    @field:Size(min = 4, max = 100)
    var password: String? = null,

    var rememberMe: Boolean = false
)