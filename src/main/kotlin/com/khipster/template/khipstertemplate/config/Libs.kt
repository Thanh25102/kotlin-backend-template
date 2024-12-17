package com.khipster.template.khipstertemplate.config

import com.khipster.template.khipstertemplate.errors.BadRequestAlertException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.server.ResponseStatusException
import java.util.Objects

/**
 * Wrap the nullable value into a ResponseEntity with an OK status.
 * If it's null, it returns a ResponseEntity with NOT_FOUND status.
 */
fun <X> X?.wrapOrNotFound(): ResponseEntity<X> {
    return this.wrapOrNotFound(null)
}

/**
 * Wrap the nullable value into a ResponseEntity with an OK status and optional headers.
 * If it's null, throws a ResponseStatusException with NOT_FOUND status.
 *
 * @param headers Optional headers to be included in the response.
 */
fun <X> X?.wrapOrNotFound(headers: HttpHeaders?): ResponseEntity<X> {
    return this?.let { response ->
        ResponseEntity.ok().apply {
            headers?.let { this.headers(it) }
        }.body(response)
    } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
}

fun <T : Any> requireIdNotNull(value: T?, lazyMessage: () -> BadRequestAlertException): T {
    if (value == null) {
        throw lazyMessage()
    } else {
        return value
    }
}

fun <T : Any> requireIdEqualNotNull(id: T?, targetId: T?, lazyMessage: () -> BadRequestAlertException): T {
    requireIdNotNull(id) { lazyMessage() }
    if (Objects.equals(id, targetId)) {
        throw lazyMessage()
    } else {
        return id!!
    }
}