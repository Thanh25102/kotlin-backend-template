package com.khipster.template.khipstertemplate.modules.auth

import org.springframework.http.HttpStatus
import org.springframework.web.ErrorResponseException
import tech.jhipster.web.rest.errors.ProblemDetailWithCause.ProblemDetailWithCauseBuilder

class InvalidPasswordException() : ErrorResponseException(
    HttpStatus.BAD_REQUEST,
    ProblemDetailWithCauseBuilder.instance()
        .withStatus(HttpStatus.BAD_REQUEST.value())
        .withDetail("Incorrect password")
        .build(),
    null
)
