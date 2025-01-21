package com.khipster.template.khipstertemplate.modules.account

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
class AccountResource(
    private val accountService: AccountService,
    private val accountQueryService: AccountQueryService
) {

    private var applicationName: String? = "test app name"

    @PostMapping("/accounts")
    fun createAccount(@RequestBody account: CreateAccountDto): ResponseEntity<ApiResponse<AccountDTO>> {
        val result = accountService.save(account)
        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityCreationAlert(
                applicationName,
                true,
                ProductResource.ENTITY_NAME,
                result.id.toString()
            )
        )
    }

    @PutMapping("/accounts/{id}")
    fun updateAccount(
        @RequestBody account: UpdateAccountDto,
        @PathVariable id: Long
    ): ResponseEntity<ApiResponse<AccountDTO>> {
        val result = accountService.update(account.apply {
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

    @PatchMapping(value = ["/accounts/{id}"], consumes = ["application/merge-patch+json"])
    fun partialUpdateAccount(
        @RequestBody account: UpdateAccountDto,
        @PathVariable id: Long
    ): ResponseEntity<ApiResponse<AccountDTO>> {
        val result = accountService.partialUpdate(account)
        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ProductResource.ENTITY_NAME,
                result.id.toString()
            )
        )
    }

    @DeleteMapping("/accounts/{id}")
    fun deActivateAccount(@PathVariable id: Long): ResponseEntity<ApiResponse<Void>> {
        accountService.deActivated(id)
        return ResponseEntity.noContent()
            .build()
    }

    @GetMapping("/accounts")
    fun getAllAccounts(criteria: AccountCriteria, @ParameterObject pageable: Pageable): ResponseEntity<ApiResponse<List<AccountDTO>>> {
        val page = accountQueryService.findByCriteria(criteria, pageable)
        val headers =
            PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page)
        return page.content.wrapOrNotFound(
            headers = headers
        )
    }

    @GetMapping("/accounts/count")
    fun countAccounts(criteria: AccountCriteria): ResponseEntity<ApiResponse<Long>> {
        return accountQueryService.countByCriteria(criteria).wrapOrNotFound()
    }

}