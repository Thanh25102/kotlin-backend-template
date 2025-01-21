package com.khipster.template.khipstertemplate.modules.faces

import com.khipster.template.khipstertemplate.modules.faces.service.FaceDetectionService
import org.springframework.web.bind.annotation.RestController

@RestController
class FaceDetectionResource(private val faceDetectionService: FaceDetectionService) {

}