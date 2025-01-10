package com.khipster.template.khipstertemplate.modules.smartcontrol

import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class CameraResource(private val cameraIntegration: CameraIntegration) {

    @GetMapping("/cameras")
    fun getCameras(): ResponseEntity<ApiResponse<List<CameraDTO>>> {
        return cameraIntegration.getCameras().wrapOrNotFound()
    }
}