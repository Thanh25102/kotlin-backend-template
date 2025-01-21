package com.khipster.template.khipstertemplate.modules.attendance.operational

import org.springdoc.core.annotations.ParameterObject
import tech.jhipster.service.Criteria
import tech.jhipster.service.filter.*
import java.io.Serializable

@ParameterObject
data class OperationalCriteria(
    var id: LongFilter? = null,
    var title: StringFilter? = null,
    var startTime: InstantFilter? = null,
    var endTime: InstantFilter? = null,
    var dayOfWeek: StringFilter? = null,

    var coefficient: FloatFilter? = null,
    var coefficientOt: FloatFilter? = null,
    var isOvernight: BooleanFilter? = null,
    var startBreakTime: InstantFilter? = null,
    var endBreakTime: InstantFilter? = null,
    var distinct: Boolean? = null
) : Serializable, Criteria {
    constructor(other: OperationalCriteria) : this(
        other.id?.copy(),
        other.title?.copy(),
        other.startTime?.copy(),
        other.endTime?.copy(),
        other.dayOfWeek?.copy(),
        other.coefficient?.copy(),
        other.coefficientOt?.copy(),
        other.isOvernight?.copy(),
        other.startBreakTime?.copy(),
        other.endBreakTime?.copy(),
        other.distinct
    )

    override fun copy(): Criteria {
        return OperationalCriteria(this)
    }

    companion object {
        private const val serialVersionUID: Long = 1L
    }

}
