package com.khipster.template.khipstertemplate.module.products

import org.springdoc.core.annotations.ParameterObject
import tech.jhipster.service.Criteria
import tech.jhipster.service.filter.BigDecimalFilter
import tech.jhipster.service.filter.LongFilter
import tech.jhipster.service.filter.StringFilter
import java.io.Serializable


@ParameterObject
data class ProductCriteria(
    var id: LongFilter? = null,
    var name: StringFilter? = null,
    var price: BigDecimalFilter? = null,
    var distinct: Boolean? = null
) : Serializable, Criteria {

    constructor(other: ProductCriteria) : this(
        other.id?.copy(), other.name?.copy(), other.price?.copy(), other.distinct
    )

    override fun copy() = ProductCriteria(this)

    companion object {
        private const val serialVersionUID: Long = 1L
    }
}