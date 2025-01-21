package com.khipster.template.khipstertemplate.modules.attendance.branch

import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import tech.jhipster.service.QueryService

@Service
@Transactional
class BranchQueryService(
    private val branchRepo: BranchRepo
) : QueryService<Branch>() {

    fun findByCriteria(criteria: BranchCriteria?): MutableList<BranchDto> {
        val specification = createSpecification(criteria)
        return branchRepo.findAll(specification).mapNotNull { it.toDto() }.toMutableList()
    }

    fun findByCriteria(criteria: BranchCriteria?, page: Pageable): Page<BranchDto> {
        val specification = createSpecification(criteria)
        return branchRepo.findAll(specification, page)
            .map { it.toDto() }
    }

    fun countByCriteria(criteria: BranchCriteria?): Long {
        val specification = createSpecification(criteria)
        return branchRepo.count(specification)
    }

    fun createSpecification(criteria: BranchCriteria?): Specification<Branch?> {
        var specification: Specification<Branch?> = Specification.where(null)

        criteria?.let {
            val (id, title, description, createdAt) = it
            val distinctCriteria = criteria.distinct

            distinctCriteria?.let {
                specification = specification.and(distinct(distinctCriteria))
            }

            id?.let {
                specification = specification.and(buildSpecification(id,Branch_.id))
            }

            title?.let {
                specification = specification.and(buildStringSpecification(title, Branch_.title))
            }

            description?.let {
                specification = specification.and(buildStringSpecification(description, Branch_.description))
            }

            createdAt?.let {
                specification = specification.and(buildRangeSpecification(createdAt, Branch_.createdAt))
            }
        }

        return specification
    }
}