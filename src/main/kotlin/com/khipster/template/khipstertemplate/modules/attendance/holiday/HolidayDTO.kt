package com.khipster.template.khipstertemplate.modules.attendance.holiday

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
    var startDate: Instant? = null,
    var endDate: Instant? = null,
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