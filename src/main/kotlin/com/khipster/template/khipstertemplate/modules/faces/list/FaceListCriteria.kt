package com.khipster.template.khipstertemplate.modules.faces.list

import org.springdoc.core.annotations.ParameterObject
import tech.jhipster.service.Criteria
import tech.jhipster.service.filter.InstantFilter
import tech.jhipster.service.filter.StringFilter
import java.io.Serializable

@ParameterObject
data class FaceListCriteria(
    // only gte and lte are used
    var createTime: InstantFilter? = null,
    var lastUpdateTime: InstantFilter? = null,
    var userData: StringFilter? = null,
) : Serializable, Criteria {

    constructor(other: FaceListCriteria) : this(
        other.createTime?.copy(),
        other.lastUpdateTime?.copy(),
        other.userData?.copy()
    )

    override fun copy() = FaceListCriteria(this)

}