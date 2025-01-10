package com.khipster.template.khipstertemplate.domain

import org.springframework.http.ResponseEntity

data class  ApiResponse<T>(
    val message: String,
    val status: Int,
    val data: T?
) {
    companion object {
        fun <T> ok(message: String, data: T?): ApiResponse<T> {
            return ApiResponse(message, 200, data)
        }

        fun <T> error(message: String, status: Int): ApiResponse<T> {
            return ApiResponse(message, status, null)
        }
    }
}