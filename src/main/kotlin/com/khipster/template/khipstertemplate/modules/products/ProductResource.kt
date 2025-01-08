package com.khipster.template.khipstertemplate.modules.products

import com.khipster.template.khipstertemplate.config.requireIdEqualNotNull
import com.khipster.template.khipstertemplate.config.requireIdNotNull
import com.khipster.template.khipstertemplate.config.toModel
import com.khipster.template.khipstertemplate.config.wrapOrNotFound
import com.khipster.template.khipstertemplate.domain.ApiResponse
import com.khipster.template.khipstertemplate.errors.BadRequestAlertException
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import tech.jhipster.web.util.HeaderUtil
import tech.jhipster.web.util.PaginationUtil
import java.net.URISyntaxException

@RestController
@RequestMapping("/api")
class ProductResource(
    private val productService: ProductService,
    private val productQueryService: ProductQueryService,
    private val productRepository: ProductRepo
) {
    companion object {
        const val ENTITY_NAME = "product"
    }

    private var applicationName: String? = "test app name"

    @PostMapping("/products")
    fun createProduct(@Valid @RequestBody productDTO: ProductDTO): ResponseEntity<ApiResponse<ProductDTO>> {
        productDTO.id = null
        val result = productService.save(productDTO)
        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.id.toString())
        )
    }

    @PutMapping("/products/{id}")
    fun updateProduct(
        @PathVariable(value = "id", required = false) id: Long,
        @Valid @RequestBody productDTO: ProductDTO
    ): ResponseEntity<ApiResponse<ProductDTO>> {

        requireIdNotNull(productDTO.id) { BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull") }

        requireIdEqualNotNull(productDTO.id, id) { BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid") }

        requireIdNotNull(productRepository.existsById(id)) {
            BadRequestAlertException(
                "Entity not found",
                ENTITY_NAME,
                "idnotfound"
            )
        }

        val result = productService.update(productDTO)
        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productDTO.id.toString())
        )
    }


    @PatchMapping(value = ["/products/{id}"], consumes = ["application/json", "application/merge-patch+json"])
    @Throws(URISyntaxException::class)
    fun partialUpdateProduct(
        @PathVariable(value = "id", required = false) id: Long,
        @NotNull @RequestBody productDTO: ProductDTO
    ): ResponseEntity<ApiResponse<ProductDTO>> {

        requireIdNotNull(productDTO.id) { BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull") }

        requireIdEqualNotNull(productDTO.id, id) { BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid") }

        requireIdNotNull(productRepository.existsById(id)) {
            BadRequestAlertException(
                "Entity not found",
                ENTITY_NAME,
                "idnotfound"
            )
        }


        val result = productService.partialUpdate(productDTO)

        return result.wrapOrNotFound(
            headers = HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productDTO.id.toString())
        )
    }

    @GetMapping("/products")
    fun getAllProducts(
        criteria: ProductCriteria,
        @ParameterObject pageable: Pageable,
        pageableAssembler: PagedResourcesAssembler<ProductDTO>
    ): ResponseEntity<ApiResponse<PagedModel<EntityModel<ProductDTO?>?>>> {
        val page = productQueryService.findByCriteria(criteria, pageable)
        val headers =
            PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page)

        throw Exception("Not implemented")
        return page.toModel(pageableAssembler).wrapOrNotFound(headers = headers)
    }

    @GetMapping("/products/count")
    fun countProducts(criteria: ProductCriteria): ResponseEntity<ApiResponse<Long>> {
        return productQueryService.countByCriteria(criteria).wrapOrNotFound()
    }

    @GetMapping("/products/{id}")
    fun getProduct(@PathVariable id: Long): ResponseEntity<ApiResponse<ProductDTO>> {
        val productDTO = productService.findOne(id)
        return productDTO.wrapOrNotFound()
    }

    @DeleteMapping("/products/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        productService.delete(id)
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build()
    }
}