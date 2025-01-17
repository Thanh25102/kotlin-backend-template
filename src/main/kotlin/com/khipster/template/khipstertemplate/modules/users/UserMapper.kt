package com.khipster.template.khipstertemplate.modules.users

import com.khipster.template.khipstertemplate.domain.Authority
import com.khipster.template.khipstertemplate.domain.User

fun User.toDto(): UserDTO = UserDTO(
    id = this.id!!,
    login = this.login!!,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    activated = this.activated!!,
    langKey = this.langKey,
    imageUrl = this.imageUrl,
    authorities = this.authorities.mapNotNull { it.name }.toSet(),
    activationKey = this.activationKey,
)

fun CreateUserDTO.toEntity(): User = User(
    login = this.login,
    password = this.password,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    activated = this.activated,
    langKey = this.langKey,
    imageUrl = this.imageUrl,
    authorities = this.authorities.map { Authority(name = it) }.toMutableSet()
)

fun UpdateUserDTO.toEntity(): User = User(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    activated = this.activated,
    langKey = this.langKey,
    imageUrl = this.imageUrl,
)
