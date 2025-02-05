package com.khipster.template.khipstertemplate.modules.warning

import com.khipster.template.khipstertemplate.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.proxy.HibernateProxy
import java.time.Instant

@Entity
@Table(name = "face_warning")
data class Warning (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "warning_name", nullable = false)
    val warningName: String,

    @Column(name = "warning_time", nullable = false)
    val warningTime: Instant,

    @Column(name = "warning_cameras", nullable = false)
    val warningCameras: String,

    @Column(name = "frame_image", nullable = false)
    val frameImage: String,

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    var status: Status
) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Warning

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id   ,   warningName = $warningName   ,   warningTime = $warningTime   ,   warningCameras = $warningCameras   ,   frameImage = $frameImage   ,   status = $status )"
    }
}