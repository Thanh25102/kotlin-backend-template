package com.khipster.template.khipstertemplate.modules.faces.detection

import org.springdoc.core.annotations.ParameterObject
import tech.jhipster.service.Criteria
import tech.jhipster.service.filter.BooleanFilter
import tech.jhipster.service.filter.Filter
import tech.jhipster.service.filter.InstantFilter
import tech.jhipster.service.filter.StringFilter
import java.io.Serializable

@ParameterObject
data class FaceDetectionCriteria(
    // only gte and lte are used
    var createTime: InstantFilter? = null,
    // only gte and lte are used
    var insertTime: InstantFilter? = null,
    // only in is used
    var sources: StringFilter? = null,
    // only in is used
    var streamIds: StringFilter? = null,
    // only in is used
    var cities: StringFilter? = null,
    // only in is used
    var areas: StringFilter? = null,
    // only in is used
    var districts: StringFilter? = null,
    var gender: BooleanFilter? = null,
) : Serializable, Criteria {

    constructor(other: FaceDetectionCriteria):this(
        other.createTime?.copy(),
        other.insertTime?.copy(),
        other.sources?.copy(),
        other.streamIds?.copy(),
        other.cities?.copy(),
        other.areas?.copy(),
        other.districts?.copy(),
        other.gender?.copy()
    )

    override fun copy() = FaceDetectionCriteria(this)
}
