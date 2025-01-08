package com.khipster.template.khipstertemplate.modules.products

fun ProductDTO.toEntity(): Product {
    return Product(
        id = id,
        name = name,
        price = price
    )
}

fun Product.toDto(): ProductDTO {
    return ProductDTO(
        id = id,
        name = name,
        price = price
    )
}