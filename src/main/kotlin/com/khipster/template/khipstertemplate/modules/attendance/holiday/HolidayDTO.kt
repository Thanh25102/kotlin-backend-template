package com.khipster.template.khipstertemplate.modules.attendance.holiday

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.Instant

data class HolidayDTO(
    var id: Long? = null,
    var title: String? = null,
    var description: String? = null,
    var startDate: Instant? = null,
    var endDate: Instant? = null,
    var coefficient: Float? = null,
)

data class CreateHolidayDTO(
    var title: String? = null,
    var description: String? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    var startDate: Instant? = null,
    var endDate: Instant? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    var coefficient: Float? = null,
)

data class UpdateHolidayDTO(
    var id: Long? = null,
    var title: String? = null,
    var description: String? = null,
    var startDate: Instant? = null,
    var endDate: Instant? = null,
    var coefficient: Float? = null,
)