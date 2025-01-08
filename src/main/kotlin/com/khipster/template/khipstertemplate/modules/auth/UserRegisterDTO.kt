package com.khipster.template.khipstertemplate.modules.auth

import org.springframework.core.serializer.Serializer

data class UserRegisterDTO(
    val login: String,
    val password: String,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val imageUrl: String?,
)