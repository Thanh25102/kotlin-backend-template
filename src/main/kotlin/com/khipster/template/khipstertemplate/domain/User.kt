package com.khipster.template.khipstertemplate.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.khipster.template.khipstertemplate.config.LOGIN_REGEX
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.hibernate.annotations.BatchSize
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import java.time.Instant

/**
 * A user.
 */
@Entity
@Table(name = "app_user")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null,

    @field:NotNull
    @field:Pattern(regexp = LOGIN_REGEX)
    @field:Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    var login: String? = null,

    @JsonIgnore
    @field:NotNull
    @field:Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    var password: String? = null,

    @field:Size(max = 50)
    @Column(name = "first_name", length = 50)
    var firstName: String? = null,

    @field:Size(max = 50)
    @Column(name = "last_name", length = 50)
    var lastName: String? = null,

    @field:Email
    @field:Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    var email: String? = null,

    @field:NotNull
    @Column(nullable = false)
    var activated: Boolean? = false,

    @field:Size(min = 2, max = 10)
    @Column(name = "lang_key", length = 10)
    var langKey: String? = null,

    @field:Size(max = 256)
    @Column(name = "image_url", length = 256)
    var imageUrl: String? = null,

    @field:Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    var activationKey: String? = null,

    @field:Size(max = 20)
    @Column(name = "reset_key", length = 20)
    var resetKey: String? = null,

    @Column(name = "reset_date")
    var resetDate: Instant? = null,
    @JsonIgnore

    @ManyToMany
    @JoinTable(
        name = "app_user_authority",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "authority_name", referencedColumnName = "name")]
    )
    @BatchSize(size = 20)
    var authorities: MutableSet<Authority> = mutableSetOf(),

    createdBy: String? = null,
    createdDate: Instant? = Instant.now(),
    lastModifiedBy: String? = null,
    lastModifiedDate: Instant? = Instant.now()

) : AbstractAuditingEntity<Long>(createdBy, createdDate, lastModifiedBy, lastModifiedDate), Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        if (other.id == null || id == null) return false

        return id == other.id
    }

    override fun hashCode() = 31

    override fun toString(): String {
        return "User(id=$id, login=$login, password=$password, firstName=$firstName, lastName=$lastName, email=$email, activated=$activated, langKey=$langKey, imageUrl=$imageUrl, activationKey=$activationKey, resetKey=$resetKey, resetDate=$resetDate, authorities=$authorities)"
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
