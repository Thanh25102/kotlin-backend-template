package com.khipster.template.khipstertemplate.module.products

import jakarta.validation.constraints.NotNull
import java.io.Serializable
import java.math.BigDecimal

data class ProductDTO(
    var id: Long? = null,
    @get:NotNull var name: String? = null,
    @get:NotNull var price: BigDecimal? = null
) : Serializable
