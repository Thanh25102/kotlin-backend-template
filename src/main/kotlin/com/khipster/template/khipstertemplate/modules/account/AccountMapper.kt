package com.khipster.template.khipstertemplate.modules.account

import com.khipster.template.khipstertemplate.domain.User

fun User.toDto(): AccountDTO {
    return AccountDTO(
        id = id,
        login = login,
        firstName = firstName,
        lastName = lastName,
        email = email,
        activated = activated,
        langKey = langKey,
        imageUrl = imageUrl,
        activationKey = activationKey,
        resetKey = resetKey,
        resetDate = resetDate,
        authorities = authorities.mapNotNull { it.name }.toSet()
    )
}