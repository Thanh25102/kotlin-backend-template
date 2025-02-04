package com.khipster.template.khipstertemplate.modules.warning

import com.khipster.template.khipstertemplate.Gender
import com.khipster.template.khipstertemplate.Mask
import org.springdoc.core.annotations.ParameterObject
import tech.jhipster.service.Criteria
import tech.jhipster.service.filter.BigDecimalFilter
import tech.jhipster.service.filter.Filter
import tech.jhipster.service.filter.InstantFilter
import tech.jhipster.service.filter.LongFilter
import tech.jhipster.service.filter.StringFilter
import java.io.Serializable

@ParameterObject
data class WarningSettingCriteria(
    var id: LongFilter? = null,
    var warningName: StringFilter? = null,
    var gender: Filter<Gender>? = null,
    var mask: Filter<Mask>? = null,
    var createdAt: InstantFilter? = null,
    var listId: StringFilter? = null,

    var distinct: Boolean? = null
) : Serializable, Criteria {

    constructor(other: WarningSettingCriteria) : this(
        other.id?.copy(),
        other.warningName?.copy(),
        other.gender?.copy(),
        other.mask?.copy(),
        other.createdAt?.copy(),
        other.listId?.copy(),
        other.distinct
    )

    override fun copy(): Criteria {
        return WarningSettingCriteria(this)
    }

}