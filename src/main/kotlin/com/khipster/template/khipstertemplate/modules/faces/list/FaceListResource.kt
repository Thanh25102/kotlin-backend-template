package com.khipster.template.khipstertemplate.modules.faces.list

import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class FaceListResource(
    private val faceListService: FaceListService,
    private val faceListQueryService: FaceListQueryService
) {

    @PostMapping("/face-lists")
    fun createFaceList(
        @RequestBody face: LunaListCreateRequest
    ): ResponseEntity<ApiResponse<LunaListCreateResponse>>? {
        return faceListService.create(face)?.wrapOrNotFound(message = "Face list not created")
    }

    @PutMapping("/face-lists")
    fun updateFaceList(
        @RequestBody face: LunaListUpdateRequest
    ): ResponseEntity<Void> {
        faceListService.update(face)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/face-lists")
    fun getAllFaceLists(
        criteria: FaceListCriteria,
        @ParameterObject pageable: Pageable
    ): ResponseEntity<ApiResponse<List<LunaListResponse>>>? {
        return faceListQueryService.fetchByCriteria(
            criteria,
            pageable
        )?.lists?.wrapOrNotFound(message = "Face list not found")
    }

}