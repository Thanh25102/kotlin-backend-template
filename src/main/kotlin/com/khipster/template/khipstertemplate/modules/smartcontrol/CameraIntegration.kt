package com.khipster.template.khipstertemplate.modules.smartcontrol

import com.khipster.template.khipstertemplate.config.getForObject
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class CameraIntegration(private val restClient: RestClient, private val tokenManagement: TokenManagement) {
    fun getCameras(): List<CameraDTO>? {
        val token = tokenManagement.getToken()
        return restClient.getForObject<IntegrationResponse>(
            uri = "/api/camera/getDevices",
            onError = {
                println("Error: $it")
                if (it.message?.contains("Unauthorized") == true){
                    tokenManagement.refreshToken()
                    getCameras()
                }
            },
            onSuccess = { println("Success: $it") },
            headers = mapOf("Authorization" to token)
        )?.data?.cameras
    }

}