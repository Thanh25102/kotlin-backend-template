package com.khipster.template.khipstertemplate.modules.attendance.holiday

import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import com.khipster.template.khipstertemplate.modules.products.ProductResource.Companion.ENTITY_NAME
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import tech.jhipster.web.util.HeaderUtil
import tech.jhipster.web.util.PaginationUtil

@RestController
@RequestMapping("/api")
class HolidayResource(
    private val holidayQueryService: HolidayQueryService,
    private val holidayService: HolidayService
) {
    private var applicationName: String? = "test app name"


    @GetMapping("/holidays")
    fun getAllHolidays(
        holidayCriteria: HolidayCriteria,
        @ParameterObject pageable: Pageable
    ): ResponseEntity<ApiResponse<List<HolidayDTO>>> {
        val page = holidayQueryService.findByCriteria(holidayCriteria, pageable)
        val headers =
            PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page)
        return page.content.wrapOrNotFound(
            headers = headers
        )
    }

    @GetMapping("/holidays/count")
    fun countHolidays(holidayCriteria: HolidayCriteria): ResponseEntity<ApiResponse<Long>> {
        return holidayQueryService.countByCriteria(holidayCriteria).wrapOrNotFound()
    }

    @PostMapping("/holidays")
    fun createHoliday(@RequestBody holidayDTO: CreateHolidayDTO): ResponseEntity<ApiResponse<HolidayDTO>> {
        val result = holidayService.save(holidayDTO)
        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.id.toString())
        )
    }

    @PutMapping("/holidays/{id}")
    fun updateHoliday(@RequestBody holidayDTO: UpdateHolidayDTO, @PathVariable id: Long): ResponseEntity<ApiResponse<HolidayDTO>> {
        val result = holidayService.update(holidayDTO.apply {
            this.id = id
        })
        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.id.toString())
        )
    }

    @DeleteMapping("/holidays/{id}")
    fun deleteHoliday(@PathVariable id: Long): ResponseEntity<ApiResponse<Void>> {
        holidayService.delete(id)
        return ResponseEntity.noContent().build()
    }

}