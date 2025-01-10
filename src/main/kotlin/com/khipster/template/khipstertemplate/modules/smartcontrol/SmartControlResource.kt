package com.khipster.template.khipstertemplate.modules.smartcontrol

import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class SmartControlResource(
    private val smartControlService: SmartControlService,
    private val smartControlQueryService: SmartControlQueryService
) {

    @GetMapping("/smart-controls")
    fun getAllSmartControls(
        smartControlCriteria: SmartControlCriteria,
        @ParameterObject pageable: Pageable
    ): ResponseEntity<ApiResponse<List<SmartControlDTO>>> {
        return smartControlQueryService.findByCriteria(
            smartControlCriteria,
            pageable
        ).content.wrapOrNotFound()
    }

    @GetMapping("/smart-controls/count")
    fun countSmartControls(smartControlCriteria: SmartControlCriteria): ResponseEntity<ApiResponse<Long>> {
        return smartControlQueryService.countByCriteria(smartControlCriteria).wrapOrNotFound()
    }

    @PostMapping("/smart-controls")
    fun createSmartControl(@RequestBody smartControlDTO: SmartControlDTO): ResponseEntity<ApiResponse<SmartControlDTO>> {
        return smartControlService.save(smartControlDTO).wrapOrNotFound()
    }

    @PutMapping("/smart-controls")
    fun updateSmartControl(@RequestBody smartControlDTO: SmartControlDTO): ResponseEntity<ApiResponse<SmartControlDTO>> {
        requireNotNull(smartControlDTO.id) { "Id not found" }
        return smartControlService.save(smartControlDTO).wrapOrNotFound()
    }


}