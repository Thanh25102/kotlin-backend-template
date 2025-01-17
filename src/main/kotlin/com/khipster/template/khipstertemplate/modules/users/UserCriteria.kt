package com.khipster.template.khipstertemplate.modules.users

import org.springdoc.core.annotations.ParameterObject
import tech.jhipster.service.Criteria
import tech.jhipster.service.filter.BooleanFilter
import tech.jhipster.service.filter.LongFilter
import tech.jhipster.service.filter.StringFilter
import java.io.Serializable

@ParameterObject
data class UserCriteria(
    var id: LongFilter? = null,
    var login: StringFilter? = null,
    var email: StringFilter? = null,
    var activated: BooleanFilter? = null,
    var distinct: Boolean? = null
): Serializable, Criteria {
    constructor(other: UserCriteria) : this(
        other.id?.copy(), other.login?.copy(), other.email?.copy(), other.activated, other.distinct
    )

    override fun copy() = UserCriteria(this)

    companion object {
        private const val serialVersionUID: Long = 1L
    }
}