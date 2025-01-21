package com.khipster.template.khipstertemplate.modules.attendance.operational

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.proxy.HibernateProxy
import java.time.Instant

@Entity
@Table(name = "face_operational_hour")
data class OperationalHour (
    @Id
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    var title: String? = null,

    @NotNull
    @Column(name = "start_time", nullable = false)
    var startTime: Instant? = null,

    @NotNull
    @Column(name = "end_time", nullable = false)
    var endTime: Instant? = null,

    @Size(max = 25)
    @NotNull
    @Column(name = "day_of_week", nullable = false, length = 25)
    var dayOfWeek: String? = null,

    @NotNull
    @Column(name = "coefficient", nullable = false)
    var coefficient: Float? = null,

    @NotNull
    @Column(name = "coefficient_ot", nullable = false)
    var coefficientOt: Float? = null,

    @NotNull
    @Column(name = "is_overnight", nullable = false)
    var isOvernight: Boolean? = false,

    @NotNull
    @Column(name = "start_break_time", nullable = false)
    var startBreakTime: Instant? = null,

    @NotNull
    @Column(name = "end_break_time", nullable = false)
    var endBreakTime: Instant? = null,

    @Column(name = "operational_group_id")
    var operationalGroupId: Long? = null,
) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as OperationalHour

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id   ,   title = $title   ,   startTime = $startTime   ,   endTime = $endTime   ,   dayOfWeek = $dayOfWeek   ,   coefficient = $coefficient   ,   coefficientOt = $coefficientOt   ,   isOvernight = $isOvernight   ,   startBreakTime = $startBreakTime   ,   endBreakTime = $endBreakTime   ,   operationalGroupId = $operationalGroupId )"
    }
}