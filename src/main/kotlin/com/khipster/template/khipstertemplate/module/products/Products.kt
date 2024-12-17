package com.khipster.template.khipstertemplate.module.products

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import java.math.BigDecimal


@Entity
@Table(name = "products")
@SuppressWarnings("common-java:DuplicatedBlocks")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @get: NotNull
    @Column(name = "name", nullable = false)
    var name: String? = null,

    @get: NotNull
    @Column(name = "price", precision = 21, scale = 2, nullable = false)
    var price: BigDecimal? = null,
) : Serializable {

    override fun hashCode(): Int = javaClass.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Product) return false
        return id != null && other.id != null && id == other.id
    }

    override fun toString() = "Product(id=$id, name=$name, price=$price)"

    companion object {
        private const val serialVersionUID = 1L
    }
}
