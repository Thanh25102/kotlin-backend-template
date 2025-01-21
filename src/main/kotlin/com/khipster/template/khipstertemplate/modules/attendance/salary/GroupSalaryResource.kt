package com.khipster.template.khipstertemplate.modules.attendance.salary

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
class GroupSalaryResource(
    private val groupSalaryService: GroupSalaryService, private val groupSalaryQueryService: GroupSalaryQueryService
) {
    private var applicationName: String? = "test app name"

    @PostMapping("/group-salaries")
    fun create(@RequestBody groupSalaryDTO: CreateGroupSalaryDTO): ResponseEntity<ApiResponse<GroupSalaryDTO>> {
        val result = groupSalaryService.create(groupSalaryDTO)
        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.id.toString())
        )
    }

    @PutMapping("/group-salaries/{id}")
    fun update(
        @RequestBody groupSalaryDTO: UpdateGroupSalaryDTO, @PathVariable("id") id: Long
    ): ResponseEntity<ApiResponse<GroupSalaryDTO>> {

        val result = groupSalaryService.update(groupSalaryDTO.apply {
            this.id = id
        })
        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.id.toString())
        )
    }

    @GetMapping("/group-salaries")
    fun getAllGroupSalaries(
        groupSalaryCriteria: GroupSalaryCriteria, @ParameterObject pageable: Pageable
    ): ResponseEntity<ApiResponse<List<GroupSalaryDTO>>> {
        val page = groupSalaryQueryService.findByCriteria(groupSalaryCriteria, pageable)
        val headers =
            PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page)
        return page.content.wrapOrNotFound(headers = headers)
    }

    @GetMapping("/group-salaries/count")
    fun countGroupSalaries(groupSalaryCriteria: GroupSalaryCriteria): ResponseEntity<ApiResponse<Long>> {
        return groupSalaryQueryService.countByCriteria(groupSalaryCriteria).wrapOrNotFound()
    }

}