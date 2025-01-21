package com.khipster.template.khipstertemplate.modules.attendance.branch

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
class BranchResource(
    private val branchService: BranchService,
    private val branchQueryService: BranchQueryService,
) {
    private var applicationName: String? = "test app name"

    @PostMapping("/branches")
    fun createBranch(@RequestBody createBranchDto: CreateBranchDto): ResponseEntity<ApiResponse<BranchDto>> {
        val result = branchService.save(createBranchDto)
        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.id.toString())
        )
    }

    @PutMapping("/branches/{id}")
    fun updateBranch(
        @PathVariable(value = "id", required = false) id: Long,
        @RequestBody updateBranchDto: UpdateBranchDto
    ): ResponseEntity<ApiResponse<BranchDto>> {
        val result = branchService.update(updateBranchDto)
        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.id.toString())
        )
    }

    @PatchMapping("/branches/{id}")
    fun partialUpdateBranch(
        @PathVariable(value = "id", required = false) id: Long,
        @RequestBody updateBranchDto: UpdateBranchDto
    ): ResponseEntity<ApiResponse<BranchDto>> {
        val branchDto = branchService.partialUpdate(updateBranchDto)
        return branchDto.wrapOrNotFound(
            headers = HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, branchDto?.id.toString())
        )
    }

    @DeleteMapping("/branches/{id}")
    fun deleteBranch(@PathVariable id: Long): ResponseEntity<Void> {
        branchService.delete(id)
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build()
    }


    @GetMapping("/branches")
    fun searchBranch(
        criteria: BranchCriteria,
        @ParameterObject pageable: Pageable, ): ResponseEntity<ApiResponse<List<BranchDto>>> {
        val page = branchQueryService.findByCriteria(criteria, pageable)
        val headers =
            PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page)

        return page.content.wrapOrNotFound(headers = headers)
    }

    @GetMapping("/branches/count")
    fun countBranch(criteria: BranchCriteria): ResponseEntity<ApiResponse<Long>> {
        return branchQueryService.countByCriteria(criteria).wrapOrNotFound()
    }
}