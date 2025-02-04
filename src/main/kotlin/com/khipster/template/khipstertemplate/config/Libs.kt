package com.khipster.template.khipstertemplate.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.khipster.template.khipstertemplate.domain.ApiResponse
import com.khipster.template.khipstertemplate.errors.BadRequestAlertException
import org.springframework.data.domain.Page
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import tech.jhipster.web.util.PaginationUtil
import java.util.*

import com.fasterxml.jackson.module.kotlin.readValue
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

/**
 * Wrap the nullable value into a ResponseEntity with an OK status.
 * If it's null, it returns a ResponseEntity with NOT_FOUND status.
 */
fun <X> X?.wrapOrNotFound(message: String? = ""): ResponseEntity<ApiResponse<X>> {
    return this.wrapOrNotFound(message, null)
}

/**
 * Wrap the nullable value into a ResponseEntity with an OK status and optional headers.
 * If it's null, throws a ResponseStatusException with NOT_FOUND status.
 *
 * @param headers Optional headers to be included in the response.
 */
fun <X> X?.wrapOrNotFound(message: String? = "", headers: HttpHeaders? = null): ResponseEntity<ApiResponse<X>> {
    return this?.let { response ->
        ResponseEntity.ok().apply {
            headers?.let { this.headers(it) }
        }.body(ApiResponse.ok(message ?: "", response))
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

fun <T> Page<T>.toModel(assembler: PagedResourcesAssembler<T>): PagedModel<EntityModel<T?>?> {
    return assembler.toModel(this)
}

fun <T> Page<T>.generatePaginationHttpHeaders(): HttpHeaders {
    return PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), this)
}

inline fun <reified T> T.toJson(): String {
    val mapper = jacksonObjectMapper()
    return mapper.writeValueAsString(this)
}

inline fun <reified T> String.fromJson(): T {
    val mapper = jacksonObjectMapper()
    return mapper.readValue(this)
}

fun Instant.toLocalTime(): LocalTime {
    return LocalDateTime.ofInstant(this, ZoneId.systemDefault()).toLocalTime()
}

fun Instant.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(this, ZoneId.systemDefault())
}

fun LocalTime.toInstant(): Instant {
    return this.atDate(Instant.now().atZone(ZoneId.systemDefault()).toLocalDate()).atZone(ZoneId.systemDefault()).toInstant()
}

fun LocalDateTime.toInstant(): Instant {
    return this.atZone(ZoneId.systemDefault()).toInstant()
}

// convert object to json snake case
fun Any.toJsonSnakeCase(): String {
    val mapper = jacksonObjectMapper()
    mapper.propertyNamingStrategy = com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE
    return mapper.writeValueAsString(this)
}

// convert object to json camel case

fun Any.toJsonCamelCase(): String {
    val mapper = jacksonObjectMapper()
    mapper.propertyNamingStrategy = com.fasterxml.jackson.databind.PropertyNamingStrategies.LOWER_CAMEL_CASE
    return mapper.writeValueAsString(this)
}