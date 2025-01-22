package com.khipster.template.khipstertemplate.modules.faces.detection

import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class FaceDetectionResource(
    private val faceDetectionQueryService: FaceDetectionQueryService
) {

    @GetMapping("/face-detection")
    fun getAllFaceDetections(
        criteria: FaceDetectionCriteria,
        @ParameterObject pageable: Pageable
    ): ResponseEntity<ApiResponse<List<LunaEventResponse>>> {
        return faceDetectionQueryService.fetchByCriteria(
            criteria,
            pageable
        )?.events.wrapOrNotFound(message = "Face detection not found")
    }

}