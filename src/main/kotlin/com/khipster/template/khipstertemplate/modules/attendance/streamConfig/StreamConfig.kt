package com.khipster.template.khipstertemplate.modules.attendance.streamConfig

import com.khipster.template.khipstertemplate.TimeSheetType
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "face_stream_detail")
data class StreamConfig(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @NotNull
    @Column(name = "stream_id", nullable = false)
    var streamId: String? = null,

    @NotNull
    @Column(name = "time_sheet_type", nullable = false, length = 1000)
    @Enumerated(EnumType.STRING)
    var timeSheetType: TimeSheetType? = null,
)