package com.khipster.template.khipstertemplate.modules.warning

import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import java.time.LocalTime

@Entity
@Table(name = "face_warning_time")
data class WarningTime(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "start_time", nullable = false)
    val startTime: LocalTime? = null,

    @Column(name = "end_time", nullable = false)
    val endTime: LocalTime? = null,

    @Column(name = "day_of_week", nullable = false)
    val dayOfWeek: String? = null,

    @ManyToOne
    @JoinColumn(name = "warning_id", nullable = false)
    var warningSettingEntity: WarningSetting? = null

) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as WarningTime

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id   ,   startTime = $startTime   ,   endTime = $endTime   ,   dayOfWeek = $dayOfWeek   ,   warningSettingEntity = $warningSettingEntity )"
    }
}
