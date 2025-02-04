package com.khipster.template.khipstertemplate.modules.warning

import com.khipster.template.khipstertemplate.config.requireIdNotNull
import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import com.khipster.template.khipstertemplate.errors.BadRequestAlertException
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import tech.jhipster.web.util.HeaderUtil
import tech.jhipster.web.util.PaginationUtil

@RestController
@RequestMapping("/api")
class WarningSettingResource(
    private val warningSettingService: WarningSettingService,
    private val warningSettingQueryService: WarningSettingQueryService,
) {

    companion object {
        const val ENTITY_NAME = "warning-setting"
    }

    private var applicationName: String? = "test app name"

    @PostMapping("/warning-settings")
    fun createWarningSetting(@RequestBody createWarningSetting: CreateWarningSetting): ResponseEntity<ApiResponse<WarningSettingDTO>> {
        val result = warningSettingService.createWarningSetting(createWarningSetting)
        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.id.toString())
        )
    }

    @PutMapping("/warning-settings/{id}")
    fun updateWarningSetting(
        @PathVariable("id") id: Long,
        @RequestBody updateWarningSetting: UpdateWarningSetting
    ): ResponseEntity<ApiResponse<WarningSettingDTO>> {

        requireIdNotNull(updateWarningSetting.id) { BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull") }

        val result = warningSettingService.updateWarningSetting(updateWarningSetting)

        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.id.toString())
        )
    }

    @GetMapping("/warning-settings")
    fun getWarningSettings(
        @ParameterObject pageable: Pageable,
        criteria: WarningSettingCriteria
    ): ResponseEntity<ApiResponse<List<WarningSettingDTO>>> {
        val page = warningSettingQueryService.findByCriteria(criteria, pageable)
        val headers =
            PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page)
        return page.content.wrapOrNotFound(headers = headers)
    }

    @GetMapping("/warning-settings/count")
    fun getWarningSettingsCount(criteria: WarningSettingCriteria): ResponseEntity<ApiResponse<Long>> {
        return warningSettingQueryService.countByCriteria(criteria).wrapOrNotFound()
    }
}