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
        @RequestBody face: FaceListCreate
    ): ResponseEntity<ApiResponse<LunaListCreateResponse>>? {
        return faceListService.create(face)?.wrapOrNotFound(message = "Face list not created")
    }

    @PatchMapping("/face-lists/{listId}")
    fun updateFaceList(
        @PathVariable listId: String,
        @RequestBody face: LunaListUpdateRequest
    ): ResponseEntity<Void> {
        faceListService.update(listId, face)
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

    @GetMapping("/face-lists/not-in-branch/{branchId}")
    fun getFaceListsNotExistInBranchId(
        @PathVariable branchId: Long
    ): ResponseEntity<ApiResponse<List<LunaListResponse>>>? {
        return faceListQueryService.fetchFaceListsNotExistInBranchId(branchId)?.wrapOrNotFound(message = "Face list not found")
    }

    @GetMapping("/face-lists/count")
    fun countFaceLists(criteria: FaceListCriteria): ResponseEntity<ApiResponse<Int>>? {
        return faceListQueryService.countByCriteria(criteria)?.wrapOrNotFound(message = "Face count list not found")
    }

}