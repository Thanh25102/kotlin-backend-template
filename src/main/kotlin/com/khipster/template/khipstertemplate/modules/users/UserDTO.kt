package com.khipster.template.khipstertemplate.modules.users

import com.khipster.template.khipstertemplate.config.LOGIN_REGEX
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

class CreateUserDTO(
    @field:NotNull
    @field:Pattern(regexp = LOGIN_REGEX)
    @field:Size(min = 1, max = 50)
    val login: String,

    @field:NotNull
    @field:Size(min = 60, max = 60)
    val password: String,

    @field:Size(max = 50)
    val firstName: String? = null,

    @field:Size(max = 50)
    val lastName: String? = null,

    @field:Email
    @field:Size(min = 5, max = 254)
    val email: String? = null,

    @field:NotNull
    val activated: Boolean,

    @field:Size(min = 2, max = 10)
    val langKey: String? = null,

    @field:Size(max = 256)
    val imageUrl: String? = null,

    val authorities: Set<String> = emptySet()
)

class UpdateUserDTO(

    @field:NotNull
    val id: Long,

    @field:Size(max = 50)
    val firstName: String? = null,

    @field:Size(max = 50)
    val lastName: String? = null,

    @field:Email
    @field:Size(min = 5, max = 254)
    val email: String? = null,

    @field:NotNull
    val activated: Boolean? = null,

    @field:Size(min = 2, max = 10)
    val langKey: String? = null,

    @field:Size(max = 256)
    val imageUrl: String? = null,

    val authorities: Set<String>? = null
)

data class UserDTO(
    val id: Long? = null,
    val login: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val activated: Boolean? = false,
    val langKey: String? = null,
    val imageUrl: String? = null,
    val authorities: Set<String>? = emptySet<String>(),
    var activationKey: String? = null,
)

