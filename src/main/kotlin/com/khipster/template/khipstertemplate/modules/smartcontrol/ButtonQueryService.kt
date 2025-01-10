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
class ButtonQueryService(private val buttonRepo: ButtonRepo) : QueryService<Buttons>() {
    private val log = LoggerFactory.getLogger(javaClass)

    fun findByCriteria(criteria: ButtonCriteria?): MutableList<ButtonDTO> {
        log.debug("find by criteria : {}", criteria)
        val specification = createSpecification(criteria)
        return buttonRepo.findAll(specification).mapNotNull { it.toDto() }.toMutableList()
    }

    fun findByCriteria(criteria: ButtonCriteria?, page: Pageable): Page<ButtonDTO> {
        log.debug("find by criteria : {}, page: {}", criteria, page)
        val specification = createSpecification(criteria)
        return buttonRepo.findAll(specification, page)
            .map { it.toDto() }
    }

    fun countByCriteria(criteria: ButtonCriteria?): Long {
        log.debug("count by criteria : {}", criteria)
        val specification = createSpecification(criteria)
        return buttonRepo.count(specification)
    }

    private fun createSpecification(criteria: ButtonCriteria?): Specification<Buttons?> {
        var specification: Specification<Buttons?> = Specification.where(null)

        criteria?.let {
            val (id, name, value, cameraId) = it
            val distinctCriteria = criteria.distinct

            distinctCriteria?.let {
                specification = specification.and(distinct(distinctCriteria))
            }
            id?.let {
                specification = specification.and(buildRangeSpecification(id, Buttons_.id))
            }
            name?.let {
                specification = specification.and(buildStringSpecification(name, Buttons_.name))
            }
            value?.let {
                specification = specification.and(buildRangeSpecification(value, Buttons_.value))
            }
            cameraId?.let {
                specification = specification.and(buildStringSpecification(cameraId, Buttons_.cameraId))
            }
        }
        return specification
    }
}