package com.khipster.template.khipstertemplate.modules.account

import java.time.Instant

data class CreateAccountDto(
    val name: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val imageUrl: String? = null,
    val login: String? = null,
    val password: String? = null,
)

data class AccountDTO(
    val id: Long? = null,
    val login: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val activated: Boolean? = null,
    val langKey: String? = null,
    var imageUrl: String? = null,
    var activationKey: String? = null,
    var resetKey: String? = null,
    var resetDate: Instant? = null,
    var authorities: Set<String> = mutableSetOf()
)

data class UpdateAccountDto(
    var id: Long? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val login: String? = null,
    val password: String? = null,
)