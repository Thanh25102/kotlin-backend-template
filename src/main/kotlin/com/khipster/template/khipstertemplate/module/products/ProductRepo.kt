package com.khipster.template.khipstertemplate.module.products

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface ProductRepo: JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}