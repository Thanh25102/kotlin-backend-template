package com.khipster.template.khipstertemplate.modules.smartcontrol

import org.springdoc.core.annotations.ParameterObject
import tech.jhipster.service.Criteria
import tech.jhipster.service.filter.IntegerFilter
import tech.jhipster.service.filter.StringFilter
import java.io.Serializable

@ParameterObject
data class SmartControlCriteria(
    var id: IntegerFilter? = null,
    var name: StringFilter? = null,
    var domain: StringFilter? = null,
    var distinct: Boolean? = null
) : Serializable, Criteria {

    constructor(other: SmartControlCriteria) : this(
        other.id?.copy(), other.name?.copy(), other.domain?.copy()
    )

    override fun copy() = SmartControlCriteria(this)

    companion object {
        private const val serialVersionUID: Long = 1L
    }
}