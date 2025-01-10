package com.khipster.template.khipstertemplate.modules.smartcontrol

import org.springdoc.core.annotations.ParameterObject
import tech.jhipster.service.Criteria
import tech.jhipster.service.filter.IntegerFilter
import tech.jhipster.service.filter.StringFilter
import java.io.Serializable

@ParameterObject
data class ButtonCriteria(
    var id: IntegerFilter? = null,
    var name: StringFilter? = null,
    var value: IntegerFilter? = null,
    var cameraId: StringFilter? = null,
    var distinct: Boolean? = null
) : Serializable, Criteria {

    constructor(other: ButtonCriteria) : this(
        other.id?.copy(), other.name?.copy(), other.value?.copy(), other.cameraId?.copy()
    )

    override fun copy() = ButtonCriteria(this)

    companion object {
        private const val serialVersionUID: Long = 1L
    }
}