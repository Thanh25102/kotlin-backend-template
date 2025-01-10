package com.khipster.template.khipstertemplate.modules.smartcontrol

import com.khipster.template.khipstertemplate.config.generatePaginationHttpHeaders
import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ButtonResource(private val btnQueryService: ButtonQueryService, private val btnService: ButtonService) {

    @GetMapping("/buttons")
    fun getAllButtons(
        buttonCriteria: ButtonCriteria,
        @ParameterObject pageable: Pageable,
    ): ResponseEntity<ApiResponse<List<ButtonDTO>>> {
        val page = btnQueryService.findByCriteria(buttonCriteria, pageable)
        return page.content.wrapOrNotFound(
            headers = page.generatePaginationHttpHeaders()
        )
    }

    @GetMapping("/buttons/{smartControlId}")
    fun getButtonsBySmartControlId(@PathVariable smartControlId: Int): ResponseEntity<ApiResponse<List<ButtonDTO>>> {
        return btnService.findBySmartControlId(smartControlId).wrapOrNotFound()
    }

    @PostMapping("/buttons")
    fun createButton(@RequestBody buttonDTO: ButtonDTO): ResponseEntity<ApiResponse<ButtonDTO>> {
        val result = btnService.save(buttonDTO)
        return result.wrapOrNotFound()
    }

    @PutMapping("/buttons")
    fun updateButton(@RequestBody buttonDTO: ButtonDTO): ResponseEntity<ApiResponse<ButtonDTO>> {
        val result = btnService.update(buttonDTO)
        return result.wrapOrNotFound()
    }

    @PutMapping("/buttons/updateAll")
    fun updateAllButtons(@RequestBody buttonDTOs: List<ButtonDTO>): ResponseEntity<ApiResponse<List<ButtonDTO>>> {
        val result = btnService.update(buttonDTOs)
        return result.wrapOrNotFound()
    }


}