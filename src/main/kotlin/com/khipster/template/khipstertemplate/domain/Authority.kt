package com.khipster.template.khipstertemplate.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.io.Serializable

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = "app_authority")
data class Authority(

    @field:NotNull
    @field:Size(max = 50)
    @Id
    @Column(length = 50)
    var name: String? = null

) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Authority) return false
        if (other.name == null || name == null) return false

        return name == other.name
    }

    override fun hashCode() = 31
    override fun toString(): String {
        return "Authority(name=$name)"
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
