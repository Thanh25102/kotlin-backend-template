package com.khipster.template.khipstertemplate.modules.smartcontrol

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.jhipster.service.QueryService

@Service
@Transactional(readOnly = true)
class SmartControlQueryService(private val smartControlRepo: SmartControlRepo) : QueryService<SmartControls>() {
    private val log = LoggerFactory.getLogger(javaClass)

    fun findByCriteria(criteria: SmartControlCriteria?): MutableList<SmartControlDTO> {
        log.debug("find by criteria : {}", criteria)
        val specification = createSpecification(criteria)
        return smartControlRepo.findAll(specification).mapNotNull { it.toDTO() }.toMutableList()
    }

    fun findByCriteria(criteria: SmartControlCriteria?, page: Pageable): Page<SmartControlDTO> {
        log.debug("find by criteria : {}, page: {}", criteria, page)
        val specification = createSpecification(criteria)
        return smartControlRepo.findAll(specification, page)
            .map { it.toDTO() }
    }

    fun countByCriteria(criteria: SmartControlCriteria?): Long {
        log.debug("count by criteria : {}", criteria)
        val specification = createSpecification(criteria)
        return smartControlRepo.count(specification)
    }

    private fun createSpecification(criteria: SmartControlCriteria?): Specification<SmartControls?> {
        var specification: Specification<SmartControls?> = Specification.where(null)

        criteria?.let {
            val (id, name, domain) = it
            val distinctCriteria = criteria.distinct

            distinctCriteria?.let {
                specification = specification.and(distinct(distinctCriteria))
            }
            id?.let {
                specification = specification.and(buildRangeSpecification(id, SmartControls_.id))
            }
            name?.let {
                specification = specification.and(buildStringSpecification(name, SmartControls_.name))
            }
            domain?.let {
                specification = specification.and(buildStringSpecification(domain, SmartControls_.domain))
            }

        }

        return specification

    }
}