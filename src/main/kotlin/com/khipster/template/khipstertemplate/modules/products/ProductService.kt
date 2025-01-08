package com.khipster.template.khipstertemplate.modules.products

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface ProductService {
    fun save(productDTO: ProductDTO): ProductDTO
    fun update(productDTO: ProductDTO): ProductDTO
    fun partialUpdate(productDTO: ProductDTO): ProductDTO?
    fun findAll(pageable: Pageable): Page<ProductDTO>
    fun findOne(id: Long): ProductDTO?
    fun delete(id: Long)
}