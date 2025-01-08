package com.khipster.template.khipstertemplate.domain

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.data.domain.Persistable
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
    var name: String? = null,
    var isPersisted: Boolean = false

) : Serializable, Persistable<String?> {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Authority) return false
        if (other.name == null || name == null) return false

        return name == other.name
    }

    override fun toString(): String {
        return "Authority(name=$name)"
    }

    override fun getId(): String? = name

    override fun isNew(): Boolean = !isPersisted

    fun setIsPersisted(): Authority {
        this.isPersisted = true
        return this
    }

    @PostLoad
    @PostPersist
    fun updateEntityState() {
        this.setIsPersisted()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
