package com.khipster.template.khipstertemplate.modules.attendance.streamConfig

fun StreamConfig.toDto() = StreamConfigDTO(
    id = id,
    streamId = streamId,
    timeSheetType = timeSheetType
)