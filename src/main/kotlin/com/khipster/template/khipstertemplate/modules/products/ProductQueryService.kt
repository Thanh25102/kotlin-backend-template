package com.khipster.template.khipstertemplate.modules.products

import com.khipster.template.khipstertemplate.utils.FilterURLQueryService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.util.UriComponentsBuilder
import tech.jhipster.service.QueryService

@Service
@Transactional(readOnly = true)
class ProductQueryService(
    private val productRepository: ProductRepo,
) : QueryService<Product>() {
    private val log = LoggerFactory.getLogger(javaClass)

    @Transactional(readOnly = true)
    fun findByCriteria(criteria: ProductCriteria?): MutableList<ProductDTO> {
        log.debug("find by criteria : {}", criteria)
        val specification = createSpecification(criteria)
        return productRepository.findAll(specification).mapNotNull { it.toDto() }.toMutableList()
    }

    @Transactional(readOnly = true)
    fun findByCriteria(criteria: ProductCriteria?, page: Pageable): Page<ProductDTO> {
        log.debug("find by criteria : {}, page: {}", criteria, page)
        val specification = createSpecification(criteria)
        return productRepository.findAll(specification, page)
            .map { it.toDto() }
    }

    fun getByCriteria(criteria: ProductCriteria?): String? {
        return buildQueryString(criteria)
    }

    @Transactional(readOnly = true)
    fun countByCriteria(criteria: ProductCriteria?): Long {
        log.debug("count by criteria : {}", criteria)
        val specification = createSpecification(criteria)
        return productRepository.count(specification)
    }

    /**
     * Function to convert [ProductCriteria] to a [Specification].
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching [Specification] of the entity.
     */
    fun createSpecification(criteria: ProductCriteria?): Specification<Product?> {
        var specification: Specification<Product?> = Specification.where(null)

        criteria?.let {
            val (id, name, price) = it
            val distinctCriteria = criteria.distinct

            distinctCriteria?.let {
                specification = specification.and(distinct(distinctCriteria))
            }
            id?.let {
                specification = specification.and(buildRangeSpecification(id, Product_.id))
            }
            name?.let {
                specification = specification.and(buildStringSpecification(name, Product_.name))
            }
            price?.let {
                specification = specification.and(buildRangeSpecification(price, Product_.price))
            }
        }

        return specification
    }

    fun buildQueryString(criteria: ProductCriteria?): String {
        val builder = UriComponentsBuilder.newInstance()
        criteria?.let {
            val (id, name, price, distinct) = it
            id?.let { FilterURLQueryService.buildURLQueryFromFilters(builder, "id", id) }
            name?.let { FilterURLQueryService.buildURLQueryFromFilters(builder, "name", name) }
            price?.let { FilterURLQueryService.buildURLQueryFromFilters(builder, "price", price) }
            distinct?.let { builder.queryParam("distinct", distinct) }
        }

        return builder.build().toUriString()
    }
}