package com.khipster.template.khipstertemplate.modules.attendance.salary

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.jhipster.service.QueryService

@Service
@Transactional(readOnly = true)
class GroupSalaryQueryService(
    private val groupSalaryRepo: GroupSalaryRepo,
) : QueryService<GroupSalary>() {

    fun findByCriteria(criteria: GroupSalaryCriteria?): MutableList<GroupSalaryDTO> {
        val specification = createSpecification(criteria)
        return groupSalaryRepo.findAll(specification).mapNotNull { it.toDto() }.toMutableList()
    }

    fun findByCriteria(criteria: GroupSalaryCriteria?, page: Pageable): Page<GroupSalaryDTO> {
        val specification = createSpecification(criteria)
        return groupSalaryRepo.findAll(specification, page)
            .map { it.toDto() }
    }

    fun countByCriteria(criteria: GroupSalaryCriteria?): Long {
        val specification = createSpecification(criteria)
        return groupSalaryRepo.count(specification)
    }

    fun createSpecification(criteria: GroupSalaryCriteria?): Specification<GroupSalary?> {
        var specification: Specification<GroupSalary?> = Specification.where(null)

        criteria?.let {
            val (id, title, description, salary, createdAt) = it
            val distinctCriteria = criteria.distinct

            distinctCriteria?.let {
                specification = specification.and(distinct(distinctCriteria))
            }

            id?.let {
                specification = specification.and(buildSpecification(id, GroupSalary_.id))
            }

            title?.let {
                specification = specification.and(buildStringSpecification(title, GroupSalary_.title))
            }

            description?.let {
                specification = specification.and(buildStringSpecification(description, GroupSalary_.description))
            }

            salary?.let {
                specification = specification.and(buildRangeSpecification(salary, GroupSalary_.salary))
            }

            createdAt?.let {
                specification = specification.and(buildRangeSpecification(createdAt, GroupSalary_.createdAt))
            }
        }

        return specification
    }

}
