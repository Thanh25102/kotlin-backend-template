package com.khipster.template.khipstertemplate.modules.faces.stream

import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class StreamResource(
    private val streamService: StreamService,
    private val streamQueryService: StreamQueryService
) {

    @GetMapping("/streams")
    fun getAllStreams(
        streamCriteria: StreamCriteria,
        @ParameterObject pageable: Pageable
    ): ResponseEntity<ApiResponse<List<LunaStreamGetByIdResponse>>>? {
        return streamQueryService.fetchByCriteria(
            streamCriteria,
            pageable
        )?.streams?.wrapOrNotFound(message = "Streams not found")
    }

    @PostMapping("/streams")
    fun createStream(
        @RequestBody stream: LunaStreamCreateRequest
    ): ResponseEntity<ApiResponse<LunaStreamCreateResponse>>? {
        return streamService.createStream(stream)?.wrapOrNotFound(message = "Stream not created")
    }

    @PutMapping("/streams/{id}")
    fun updateStream(
        @RequestBody stream: LunaStreamCreateRequest, @PathVariable id: String
    ): ResponseEntity<ApiResponse<Void>> {
        streamService.updateStream(id, stream)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/streams/{id}")
    fun deleteStream(
        @PathVariable id: String
    ): ResponseEntity<ApiResponse<Void>> {
        streamService.deleteStream(id)
        return ResponseEntity.ok().build()
    }
}