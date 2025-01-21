package com.khipster.template.khipstertemplate.modules.account

import org.springdoc.core.annotations.ParameterObject
import tech.jhipster.service.Criteria
import tech.jhipster.service.filter.BooleanFilter
import tech.jhipster.service.filter.LongFilter
import tech.jhipster.service.filter.StringFilter
import java.io.Serializable

@ParameterObject
data class AccountCriteria(
    var id: LongFilter? = null,
    var login: StringFilter? = null,
    var firstName: StringFilter? = null,
    var lastName: StringFilter? = null,
    var email: StringFilter? = null,
    var activated: BooleanFilter? = null,
    var distinct: Boolean? = null
) : Serializable, Criteria {

    constructor(
        other: AccountCriteria
    ) : this(
        other.id?.copy(),
        other.login?.copy(),
        other.firstName?.copy(),
        other.lastName?.copy(),
        other.email?.copy(),
        other.activated?.copy(),
        other.distinct
    )

    override fun copy(): Criteria {
        return AccountCriteria(this)
    }

    companion object {
        private const val serialVersionUID: Long = 1L
    }

}
