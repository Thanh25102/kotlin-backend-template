package com.khipster.template.khipstertemplate.modules.warning

import com.fasterxml.jackson.annotation.JsonIgnore
import com.khipster.template.khipstertemplate.Gender
import com.khipster.template.khipstertemplate.Mask
import com.khipster.template.khipstertemplate.Status
import com.khipster.template.khipstertemplate.StringListConverter
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "face_warning_setting")
data class WarningSetting(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "warning_name", nullable = false)
    var warningName: String? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: Status? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var gender: Gender? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var mask: Mask? = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant? = null,

    @Column(name = "list_id", nullable = true)
    var listId: String? = null,

    @Column(name = "range_age", nullable = true)
    var rangeAge: String? = null,

    @OneToMany(
        mappedBy = "warningSettingEntity",
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    @JsonIgnore
    var warningTimeEntities: MutableSet<WarningTime> = mutableSetOf(),

    @Column(name = "warning_cameras", nullable = true)
    @Convert(converter = StringListConverter::class)
    var warningCameras: List<String>? = null
) {

    fun addAllWarningTime(warningTimes: MutableSet<WarningTime>) {
        warningTimes.forEach { addWarningTime(it) }
    }

    fun addWarningTime(warningTime: WarningTime) {
        warningTime.warningSettingEntity = this
        warningTimeEntities.add(warningTime)
    }

    fun removeWarningTime(warningTime: WarningTime) {
        warningTimeEntities.remove(warningTime)
        warningTime.warningSettingEntity = null
    }
}
