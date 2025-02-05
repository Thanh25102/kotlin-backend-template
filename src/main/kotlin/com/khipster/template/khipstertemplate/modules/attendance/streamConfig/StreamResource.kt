package com.khipster.template.khipstertemplate.modules.attendance.streamConfig

import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class StreamConfigResource(
    private val streamConfigService: StreamConfigService
) {

    @GetMapping("/stream-configs")
    fun findAllStreamConfigs(): ResponseEntity<ApiResponse<List<StreamConfigDTO>>> {
        return streamConfigService.findAllStreamConfigs().wrapOrNotFound(
            message = "Stream Configs retrieved successfully"
        )
    }

    @PostMapping("/stream-configs")
    fun createStreamConfig(@RequestBody streamConfigCreate: StreamConfigCreate): ResponseEntity<ApiResponse<StreamConfigDTO>> {
        return streamConfigService.createStreamConfig(streamConfigCreate).wrapOrNotFound(
            message = "Stream Config created successfully"
        )
    }

    @PutMapping("/stream-configs/{id}")
    fun updateStreamConfig(@RequestBody streamConfigUpdate: StreamConfigUpdate): ResponseEntity<ApiResponse<StreamConfigDTO>> {
        return streamConfigService.updateStreamConfig(streamConfigUpdate).wrapOrNotFound(
            message = "Stream Config updated successfully"
        )
    }

}