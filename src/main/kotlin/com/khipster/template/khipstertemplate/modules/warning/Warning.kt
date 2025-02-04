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
)