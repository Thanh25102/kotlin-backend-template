package com.khipster.template.khipstertemplate.modules.attendance.holiday

import org.springdoc.core.annotations.ParameterObject
import tech.jhipster.service.Criteria
import tech.jhipster.service.filter.FloatFilter
import tech.jhipster.service.filter.InstantFilter
import tech.jhipster.service.filter.LongFilter
import tech.jhipster.service.filter.StringFilter
import java.io.Serializable

@ParameterObject
data class HolidayCriteria(
    var id: LongFilter? = null,
    var title: StringFilter? = null,
    var description: StringFilter? = null,
    var startDate: InstantFilter? = null,
    var endDate: InstantFilter? = null,
    var coefficient: FloatFilter? = null,
    var distinct: Boolean? = null
) : Serializable, Criteria {

    constructor(
        other: HolidayCriteria
    ) : this(
        other.id?.copy(),
        other.title?.copy(),
        other.description?.copy(),
        other.startDate?.copy(),
        other.endDate?.copy(),
        other.coefficient?.copy(),
        other.distinct
    )

    override fun copy(): Criteria {
        return HolidayCriteria(this)
    }

    companion object {
        private const val serialVersionUID: Long = 1L
    }
}