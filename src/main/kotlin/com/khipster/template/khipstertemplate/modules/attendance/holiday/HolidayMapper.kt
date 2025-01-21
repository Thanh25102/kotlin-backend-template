package com.khipster.template.khipstertemplate.modules.attendance.holiday

fun Holiday.toDto(): HolidayDTO {
    return HolidayDTO(
        id = id,
        title = title,
        description = description,
        startDate = startDate,
        endDate = endDate,
        coefficient = coefficient
    )
}

