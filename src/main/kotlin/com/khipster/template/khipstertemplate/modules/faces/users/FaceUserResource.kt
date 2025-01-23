package com.khipster.template.khipstertemplate.modules.faces.users

import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api")
class FaceUserResource(
    private val faceUserService: FaceUserService,
    private val faceUserQueryService: FaceUserQueryService
) {

    @GetMapping("/face-user")
    fun getAllFaceUsers(
        criteria: FaceUserCriteria,
        @ParameterObject pageable: Pageable
    ): ResponseEntity<ApiResponse<List<LunaFaceResponse>>> {
        return faceUserQueryService.fetchByCriteria(
            criteria,
            pageable
        )?.faces.wrapOrNotFound(message = "Face user not found")
    }

    @GetMapping("/face-user/count")
    fun getFaceUserCount(criteria: FaceUserCriteria): ResponseEntity<ApiResponse<Int>>? {
        return faceUserQueryService.fetchCountByCriteria(criteria)
            ?.wrapOrNotFound(message = "Face user count not found")
    }

    @PostMapping("/face-user/detect", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun detectFace(@RequestParam("image") image: MultipartFile): ResponseEntity<ApiResponse<LunaFaceResponse>>? {
        return faceUserService.detectFace(image)?.wrapOrNotFound(message = "Face not detected")
    }

    @PostMapping("/face-user")
    fun createFaceUser(face: LunaFacesCreateRequest): ResponseEntity<ApiResponse<LunaFaceCreateResponse>>? {
        return faceUserService.createFace(face)?.wrapOrNotFound(message = "Face user not created")
    }

    @PatchMapping("/face-user")
    fun updateFaceUser(face: LunaFacesUpdateRequest): ResponseEntity<ApiResponse<Void>> {
        faceUserService.updateFace(face)
        return ResponseEntity.noContent().build()
    }
}