package com.khipster.template.khipstertemplate.modules.warning

import java.time.LocalTime

data class WarningTimeDTO(
    val id: Long? = null,
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null,
    val dayOfWeek: String? = null,
)
