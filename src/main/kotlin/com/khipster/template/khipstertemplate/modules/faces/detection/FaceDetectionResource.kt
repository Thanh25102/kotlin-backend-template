package com.khipster.template.khipstertemplate.modules.faces.detection

import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class FaceDetectionResource(
    private val faceDetectionQueryService: FaceDetectionQueryService
) {

    @GetMapping("/face-detection")
    fun search(criteria: FaceDetectionCriteria, @ParameterObject pageable: Pageable) {
        println("query string : ${faceDetectionQueryService.fetchByCriteria(criteria, pageable)}")
    }

}