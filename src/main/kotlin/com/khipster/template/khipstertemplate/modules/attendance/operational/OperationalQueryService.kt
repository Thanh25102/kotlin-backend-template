package com.khipster.template.khipstertemplate.modules.attendance.operational

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.jhipster.service.QueryService

@Service
@Transactional(readOnly = true)
class OperationalQueryService(
    private val operationalRepo: OperationalRepo
) : QueryService<OperationalHour>() {

    fun findByCriteria(criteria: OperationalCriteria?): MutableList<OperationalDTO> {
        val specification = createSpecification(criteria)
        return operationalRepo.findAll(specification).mapNotNull { it.toDto() }.toMutableList()
    }

    fun findByCriteria(criteria: OperationalCriteria?, page: Pageable): Page<OperationalDTO> {
        val specification = createSpecification(criteria)
        return operationalRepo.findAll(specification, page)
            .map { it.toDto() }
    }

    fun countByCriteria(criteria: OperationalCriteria?): Long {
        val specification = createSpecification(criteria)
        return operationalRepo.count(specification)
    }

    fun createSpecification(criteria: OperationalCriteria?): Specification<OperationalHour?> {
        var specification: Specification<OperationalHour?> = Specification.where(null)

        criteria?.let {
            val (id, title, startTime, endTime, dayOfWeek, coefficient, coefficientOt, isOvernight, startBreakTime, endBreakTime) = it
            val distinctCriteria = criteria.distinct

            distinctCriteria?.let {
                specification = specification.and(distinct(distinctCriteria))
            }

            id?.let {
                specification = specification.and(buildSpecification(id, OperationalHour_.id))
            }

            title?.let {
                specification = specification.and(buildStringSpecification(title, OperationalHour_.title))
            }

            startTime?.let {
                specification = specification.and(buildRangeSpecification(startTime, OperationalHour_.startTime))
            }

            endTime?.let {
                specification = specification.and(buildRangeSpecification(endTime, OperationalHour_.endTime))
            }

            dayOfWeek?.let {
                specification = specification.and(buildSpecification(dayOfWeek, OperationalHour_.dayOfWeek))
            }

            coefficient?.let {
                specification = specification.and(buildRangeSpecification(coefficient, OperationalHour_.coefficient))
            }

            coefficientOt?.let {
                specification =
                    specification.and(buildRangeSpecification(coefficientOt, OperationalHour_.coefficientOt))
            }

            isOvernight?.let {
                specification = specification.and(buildSpecification(isOvernight, OperationalHour_.isOvernight))
            }

            startBreakTime?.let {
                specification =
                    specification.and(buildRangeSpecification(startBreakTime, OperationalHour_.startBreakTime))
            }

            endBreakTime?.let {
                specification = specification.and(buildRangeSpecification(endBreakTime, OperationalHour_.endBreakTime))
            }
        }

        return specification
    }

}