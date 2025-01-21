package com.khipster.template.khipstertemplate.modules.attendance.operational

fun OperationalHour.toDto() = OperationalDTO(
    id = id,
    title = title,
    startTime = startTime,
    endTime = endTime,
    dayOfWeek = dayOfWeek,
    coefficient = coefficient,
    coefficientOt = coefficientOt,
    isOvernight = isOvernight,
    startBreakTime = startBreakTime,
    endBreakTime = endBreakTime,
)