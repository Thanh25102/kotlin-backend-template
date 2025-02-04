package com.khipster.template.khipstertemplate.modules.attendance.operational

import com.khipster.template.khipstertemplate.config.toLocalTime

fun OperationalHour.toDto() = OperationalDTO(
    id = id,
    title = title,
    startTime = startTime?.toLocalTime(),
    endTime = endTime?.toLocalTime(),
    dayOfWeek = dayOfWeek,
    coefficient = coefficient,
    coefficientOt = coefficientOt,
    isOvernight = isOvernight,
    startBreakTime = startBreakTime?.toLocalTime(),
    endBreakTime = endBreakTime?.toLocalTime(),
)