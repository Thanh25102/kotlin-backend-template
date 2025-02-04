package com.khipster.template.khipstertemplate.modules.warning

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.jhipster.service.QueryService

@Service
@Transactional(readOnly = true)
class WarningSettingQueryService(
    private val warningSettingRepo: WarningSettingRepo
) : QueryService<WarningSetting>() {

    fun findByCriteria(criteria: WarningSettingCriteria?): MutableList<WarningSettingDTO> {
        val specification = createSpecification(criteria)
        return warningSettingRepo.findAll(specification).mapNotNull { it.toDto() }.toMutableList()
    }

    fun findByCriteria(criteria: WarningSettingCriteria?, page: Pageable): Page<WarningSettingDTO> {
        val specification = createSpecification(criteria)
        return warningSettingRepo.findAll(specification, page).map { it.toDto() }
    }

    fun countByCriteria(criteria: WarningSettingCriteria?): Long {
        val specification = createSpecification(criteria)
        return warningSettingRepo.count(specification)
    }

    fun createSpecification(criteria: WarningSettingCriteria?): Specification<WarningSetting?> {
        var specification: Specification<WarningSetting?> = Specification.where(null)

        criteria?.let {
            val (
                id, warningName, gender, mask, createdAt, listId,
            ) = criteria
            val distinctCriteria = criteria.distinct

            distinctCriteria?.let {
                specification = specification.and(distinct(distinctCriteria))
            }

            id?.let {
                specification = specification.and(buildRangeSpecification(id, WarningSetting_.id))
            }

            warningName?.let {
                specification = specification.and(buildStringSpecification(warningName, WarningSetting_.warningName))
            }

            gender?.let {
                specification = specification.and(buildSpecification(gender, WarningSetting_.gender))
            }

            mask?.let {
                specification = specification.and(buildSpecification(mask, WarningSetting_.mask))
            }

            createdAt?.let {
                specification = specification.and(buildRangeSpecification(createdAt, WarningSetting_.createdAt))
            }

            listId?.let {
                specification = specification.and(buildStringSpecification(listId, WarningSetting_.listId))
            }
        }

        return specification
    }
}