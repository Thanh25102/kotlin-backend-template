package com.khipster.template.khipstertemplate.modules.attendance.holiday

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.jhipster.service.QueryService

@Service
@Transactional(readOnly = true)
class HolidayQueryService(
    private val holidayRepo: HolidayRepo
): QueryService<Holiday>() {


    fun findByCriteria(criteria: HolidayCriteria?): MutableList<HolidayDTO> {
        val specification = createSpecification(criteria)
        return holidayRepo.findAll(specification).mapNotNull { it.toDto() }.toMutableList()
    }

    fun findByCriteria(criteria: HolidayCriteria?, page: Pageable): Page<HolidayDTO> {
        val specification = createSpecification(criteria)
        return holidayRepo.findAll(specification, page)
            .map { it.toDto() }
    }

    fun countByCriteria(criteria: HolidayCriteria?): Long {
        val specification = createSpecification(criteria)
        return holidayRepo.count(specification)
    }

    fun createSpecification(criteria: HolidayCriteria?): Specification<Holiday?> {
        var specification: Specification<Holiday?> = Specification.where(null)

        criteria?.let {
            val (id, title, description, startDate, endDate, coefficient) = it
            val distinctCriteria = criteria.distinct

            distinctCriteria?.let {
                specification = specification.and(distinct(distinctCriteria))
            }

            id?.let {
                specification = specification.and(buildSpecification(id, Holiday_.id))
            }

            title?.let {
                specification = specification.and(buildStringSpecification(title, Holiday_.title))
            }

            description?.let {
                specification = specification.and(buildStringSpecification(description, Holiday_.description))
            }

            startDate?.let {
                specification = specification.and(buildRangeSpecification(startDate, Holiday_.startDate))
            }

            endDate?.let {
                specification = specification.and(buildRangeSpecification(endDate, Holiday_.endDate))
            }

            coefficient?.let {
                specification = specification.and(buildRangeSpecification(coefficient, Holiday_.coefficient))
            }
        }

        return specification
    }

}