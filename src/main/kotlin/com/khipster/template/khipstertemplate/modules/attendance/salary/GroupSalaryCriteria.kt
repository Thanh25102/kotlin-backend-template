package com.khipster.template.khipstertemplate.modules.attendance.salary

import org.springdoc.core.annotations.ParameterObject
import tech.jhipster.service.Criteria
import tech.jhipster.service.filter.BigDecimalFilter
import tech.jhipster.service.filter.InstantFilter
import tech.jhipster.service.filter.LongFilter
import tech.jhipster.service.filter.StringFilter
import java.io.Serializable

@ParameterObject
data class GroupSalaryCriteria(
    var id: LongFilter? = null,
    var title: StringFilter? = null,
    var description: StringFilter? = null,
    var salary: BigDecimalFilter? = null,
    var createdAt: InstantFilter? = null,
    var distinct: Boolean? = null
) : Serializable, Criteria {

    constructor(
        other: GroupSalaryCriteria
    ) : this(
        other.id?.copy(),
        other.title?.copy(),
        other.description?.copy(),
        other.salary?.copy(),
        other.createdAt?.copy(),
        other.distinct
    )

    override fun copy(): GroupSalaryCriteria {
        return GroupSalaryCriteria(this)
    }

    companion object {
        private const val serialVersionUID: Long = 1L
    }
}