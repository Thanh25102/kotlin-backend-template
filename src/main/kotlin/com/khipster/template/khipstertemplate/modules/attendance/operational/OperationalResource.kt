package com.khipster.template.khipstertemplate.modules.attendance.operational

import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import com.khipster.template.khipstertemplate.modules.products.ProductResource
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import tech.jhipster.web.util.HeaderUtil
import tech.jhipster.web.util.PaginationUtil

@RestController
@RequestMapping("/api")
class OperationalResource(
    private val operationalService: OperationalService,
    private val operationalQueryService: OperationalQueryService
) {

    private var applicationName: String? = "test app name"

    @PostMapping("/operational")
    fun createOperational(@RequestBody operationalHour: CreateOperationalDTO): ResponseEntity<ApiResponse<OperationalDTO>> {
        val result = operationalService.create(operationalHour)

        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityCreationAlert(
                applicationName,
                true,
                ProductResource.ENTITY_NAME,
                result.id.toString()
            )
        )
    }

    @PutMapping("/operational/{id}")
    fun updateOperational(
        @RequestBody operationalHour: UpdateOperationalDTO,
        @PathVariable id: Long
    ): ResponseEntity<ApiResponse<OperationalDTO>> {
        val result = operationalService.update(operationalHour.apply {
            this.id = id
        })
        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ProductResource.ENTITY_NAME,
                result.id.toString()
            )
        )
    }

    @DeleteMapping("/operational/{id}")
    fun deleteOperational(@PathVariable id: Int): ResponseEntity<ApiResponse<Void>> {
        operationalService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/operational")
    fun getAllOperational(
        operationalCriteria: OperationalCriteria,
        @ParameterObject pageable: Pageable
    ): ResponseEntity<ApiResponse<List<OperationalDTO>>> {
        val page = operationalQueryService.findByCriteria(operationalCriteria, pageable)
        return page.content.wrapOrNotFound(
            headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page)
        )
    }


}