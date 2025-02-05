package com.khipster.template.khipstertemplate.modules.attendance.streamConfig

import com.khipster.template.khipstertemplate.TimeSheetType

data class StreamConfigDTO(
    val id: Long? = null,
    val streamId: String? = null,
    val timeSheetType: TimeSheetType? = null,
)

data class StreamConfigCreate(
    val streamId: String? = null,
    val timeSheetType: TimeSheetType? = null,
)

data class StreamConfigUpdate(
    val id: Long? = null,
    val streamId: String? = null,
    val timeSheetType: TimeSheetType? = null,
)