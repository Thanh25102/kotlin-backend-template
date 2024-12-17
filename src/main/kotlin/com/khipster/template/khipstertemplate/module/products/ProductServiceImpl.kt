package com.khipster.template.khipstertemplate.module.products

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.jhipster.service.QueryService

@Service
class ProductServiceImpl(
    private val productRepo: ProductRepo,
) : ProductService, QueryService<Product>() {

    private val log = LoggerFactory.getLogger(javaClass)

    @Transactional(readOnly = false)
    override fun save(productDTO: ProductDTO): ProductDTO {
        if (productDTO.id != null) throw RuntimeException("A new product cannot already have an ID")
        return productRepo.save(productDTO.toEntity()).toDto()
    }

    @Transactional(readOnly = false)
    override fun update(productDTO: ProductDTO): ProductDTO {
        if (productDTO.id == null) throw RuntimeException("Cannot update a product without an ID")
        return productRepo.save(productDTO.toEntity()).toDto()
    }

    @Transactional(readOnly = false)
    override fun partialUpdate(productDTO: ProductDTO): ProductDTO? {
        return productRepo.findByIdOrNull(productDTO.id)?.let {
            val updated = it.copy(
                name = productDTO.name,
                price = productDTO.price
            )
            productRepo.save(updated).toDto()
        }
    }

    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable): Page<ProductDTO> {
        return productRepo.findAll(pageable).map { it.toDto() }
    }

    @Transactional(readOnly = true)
    override fun findOne(id: Long): ProductDTO? {
        return productRepo.findByIdOrNull(id)?.toDto()
    }

    @Transactional(readOnly = false)
    override fun delete(id: Long) {
        productRepo.deleteById(id)
    }
}