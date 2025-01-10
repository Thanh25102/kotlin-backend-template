package com.khipster.template.khipstertemplate.config

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

inline fun <reified T> RestTemplate.get(
    uri: String = "",
    params: Map<String, Any> = emptyMap(),
    onError: (Throwable) -> Unit = {},
    onSuccess: (T) -> Unit = {},
    headers: Map<String, String> = emptyMap()
): T? {
    return try {
        // Xây dựng URI với query parameters
        val uriBuilder = UriComponentsBuilder.fromUriString(uri)
        params.forEach { (key, value) -> uriBuilder.queryParam(key, value) }
        val finalUri = uriBuilder.build().toUriString()

        // Tạo HttpHeaders
        val httpHeaders = HttpHeaders()
        headers.forEach { (key, value) ->
            httpHeaders[key] = value
        }

        // Request entity rỗng (GET thường không có body)
        val requestEntity = HttpEntity<Unit>(httpHeaders)

        // Gọi API
        val response = this.exchange(
            finalUri,
            HttpMethod.GET,
            requestEntity,
            object : ParameterizedTypeReference<T>() {}
        )

        response.body?.also(onSuccess)
    } catch (ex: Exception) {
        onError(ex)
        null
    }
}

/**
 * Extension function POST cho RestTemplate.
 * Tự động gắn header, gửi body, xử lý onSuccess/onError.
 */
inline fun <reified T> RestTemplate.post(
    uri: String = "",
    requestBody: Any,
    onError: (Throwable) -> Unit = {},
    onSuccess: (T) -> Unit = {},
    headers: Map<String, String> = emptyMap(),
): T? {
    return try {
        val httpHeaders = HttpHeaders()
        headers.forEach { (key, value) ->
            httpHeaders[key] = value
        }

        val requestEntity = HttpEntity(requestBody, httpHeaders)

        val response = this.exchange(
            uri,
            HttpMethod.POST,
            requestEntity,
            object : ParameterizedTypeReference<T>() {}
        )

        response.body?.also(onSuccess)
    } catch (ex: Exception) {
        onError(ex)
        null
    }
}

/**
 * Extension function PUT cho RestTemplate.
 */
inline fun <reified T> RestTemplate.put(
    uri: String,
    requestBody: Any,
    onError: (Throwable) -> Unit = {},
    onSuccess: (T) -> Unit = {},
    headers: Map<String, String> = emptyMap()
): T? {
    return try {
        val httpHeaders = HttpHeaders()
        headers.forEach { (key, value) ->
            httpHeaders[key] = value
        }

        val requestEntity = HttpEntity(requestBody, httpHeaders)

        val response = this.exchange(
            uri,
            HttpMethod.PUT,
            requestEntity,
            object : ParameterizedTypeReference<T>() {}
        )

        response.body?.also(onSuccess)
    } catch (ex: Exception) {
        onError(ex)
        null
    }
}

/**
 * Extension function DELETE cho RestTemplate.
 */
fun RestTemplate.delete(
    uri: String,
    headers: Map<String, String> = emptyMap(),
    onError: (Throwable) -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    try {
        val httpHeaders = HttpHeaders()
        headers.forEach { (key, value) ->
            httpHeaders[key] = value
        }

        val requestEntity = HttpEntity<Unit>(httpHeaders)

        this.exchange(
            uri,
            HttpMethod.DELETE,
            requestEntity,
            object : ParameterizedTypeReference<Unit>() {}
        )
        onSuccess()
    } catch (ex: Exception) {
        onError(ex)
    }
}
