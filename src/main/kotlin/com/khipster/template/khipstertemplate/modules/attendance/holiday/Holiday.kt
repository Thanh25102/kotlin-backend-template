package com.khipster.template.khipstertemplate.modules.attendance.holiday

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.proxy.HibernateProxy
import java.time.Instant

@Entity
@Table(name = "face_holiday")
data class Holiday (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    var title: String? = null,

    @Size(max = 1000)
    @NotNull
    @Column(name = "description", nullable = false, length = 1000)
    var description: String? = null,

    @NotNull
    @Column(name = "start_date", nullable = false)
    var startDate: Instant? = null,

    @NotNull
    @Column(name = "end_date", nullable = false)
    var endDate: Instant? = null,

    @NotNull
    @Column(name = "coefficient", nullable = false)
    var coefficient: Float? = null,
) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Holiday

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id   ,   title = $title   ,   description = $description   ,   startDate = $startDate   ,   endDate = $endDate   ,   coefficient = $coefficient )"
    }
}