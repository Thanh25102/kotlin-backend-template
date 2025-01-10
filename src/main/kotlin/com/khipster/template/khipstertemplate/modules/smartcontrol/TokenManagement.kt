package com.khipster.template.khipstertemplate.modules.smartcontrol

import com.khipster.template.khipstertemplate.config.postForObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

/**
 * This class is responsible for managing the token for the VMS API.
 * Concurrency primitives are used to ensure that the token is only fetched once.
 * Avoid race conditions by using synchronized block.
 * Do by ManhThanh
 */
@Component
class TokenManagement(private val restClient: RestClient) {
    @Value("\${third-party.vms-api.url}")
    private lateinit var vmsBaseUrl: String
    @Value("\${third-party.vms-api.username}")
    private lateinit var username: String
    @Value("\${third-party.vms-api.password}")
    private lateinit var password: String

    @Volatile
    private var token: String? = null

    private val lock = Any()

    fun getToken(): String {
        synchronized(lock) {
            token?.let { return it }
            token = fetchAuthToken()
            return token!!
        }
    }

    fun refreshToken() {
        synchronized(lock) {
            token = fetchAuthToken()
        }
    }

    fun invalidateToken() {
        synchronized(lock) {
            token = null
        }
    }

    private fun fetchAuthToken(): String? {
        val result = restClient.postForObject<VmsApiResponse<TokenData>>(
            uri = "$vmsBaseUrl/api/auth/login",
            requestBody = UserLogin(username, password),
            onError = { println("Error: $it") },
            onSuccess = { println("Success: $it") }
        )
        val token = result?.data?.token
        return token
    }
}

data class UserLogin(
    val username: String,
    val password: String
)

data class TokenData(
    val token: String
)

data class VmsApiResponse<T>(
    val data: T
)
