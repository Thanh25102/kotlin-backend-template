package com.khipster.template.khipstertemplate.modules.faces

import org.springdoc.core.annotations.ParameterObject
import tech.jhipster.service.Criteria
import tech.jhipster.service.filter.StringFilter
import java.io.Serializable

@ParameterObject
data class FaceListCriteria(
    var accountId: StringFilter? = null,
    var createTime: StringFilter? = null,
    var lastUpdateTime: StringFilter? = null,
    var userData: StringFilter? = null,
    var listId: StringFilter? = null
) : Serializable, Criteria {
    constructor(other: FaceListCriteria) : this(
        other.accountId?.copy(),
        other.createTime?.copy(),
        other.lastUpdateTime?.copy(),
        other.userData?.copy(),
        other.listId?.copy(),
    )

    override fun copy(): FaceListCriteria {
        return FaceListCriteria(this)
    }

    companion object {
        private const val serialVersionUID: Long = 1L
    }
}