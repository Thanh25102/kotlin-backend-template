package com.khipster.template.khipstertemplate.config

import org.springframework.core.ParameterizedTypeReference
import org.springframework.web.client.RestClient

inline fun <reified T> RestClient.getForObject(
    uri: String = "",
    params: Map<String, Any> = emptyMap(),
    onError: (Throwable) -> Unit = {},
    onSuccess: (T) -> Unit = {},
    headers: Map<String, String> = emptyMap(),
): T? {
    return try {
        val response = this.get()
            .uri(uri) { uriBuilder ->
                params.forEach { (key, value) ->
                    uriBuilder.queryParam(key, value)
                }
                uriBuilder.build()
            }
            .headers {
                headers.forEach { (key, value) ->
                    it.set(key, value)
                }
            }
            .retrieve()
            .body(object : ParameterizedTypeReference<T>() {})
        response?.also { onSuccess(it) }
    } catch (ex: Exception) {
        onError(ex)
        null
    }
}


inline fun <reified T> RestClient.postForObject(
    uri: String = "",
    requestBody: Any,
    onError: (Throwable) -> Unit = {},
    onSuccess: (T) -> Unit = {},
    headers: Map<String, String> = emptyMap(),
): T? {
    return try {
        val response = this.post()
            .uri(uri)
            .headers {
                headers.forEach { (key, value) ->
                    it.set(key, value)
                }
            }
            .body(requestBody)
            .retrieve()
            .body(object : ParameterizedTypeReference<T>() {})
        response?.also { onSuccess(it) }
    } catch (ex: Exception) {
        onError(ex)
        null
    }
}

inline fun <reified T> RestClient.putForObject(uri: String, requestBody: Any): T? {
    return this.put()
        .uri(uri)
        .body(requestBody)
        .retrieve()
        .body(object : ParameterizedTypeReference<T>() {})
}


fun RestClient.deleteForObject(uri: String) {
    this.delete()
        .uri(uri)
        .retrieve()
        .toBodilessEntity()
}

