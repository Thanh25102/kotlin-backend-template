package com.khipster.template.khipstertemplate.modules.faces.users

import org.springdoc.core.annotations.ParameterObject
import tech.jhipster.service.Criteria
import tech.jhipster.service.filter.InstantFilter
import tech.jhipster.service.filter.RangeFilter
import tech.jhipster.service.filter.StringFilter
import java.io.Serializable

@ParameterObject
data class FaceUserCriteria(
    var accountId: StringFilter? = null,
    var createTime: InstantFilter? = null,
    var eventId: StringFilter? = null,
    var faceId: RangeFilter<String>? = null,
    var faceIds: StringFilter? = null,
    var externalIds: StringFilter? = null,
    var userData: StringFilter? = null,
    var listId: StringFilter? = null,
) : Serializable, Criteria {

    constructor(other: FaceUserCriteria) : this(
        other.accountId?.copy(),
        other.createTime?.copy(),
        other.eventId?.copy(),
        other.faceId?.copy(),
        other.faceIds?.copy(),
        other.externalIds?.copy(),
        other.userData?.copy(),
        other.listId?.copy()
    )

    override fun copy() = FaceUserCriteria(this)
}