package com.khipster.template.khipstertemplate.modules.attendance.operational

import java.time.Instant

data class CreateOperationalDTO(
    var title: String? = null,
    var startTime: Instant? = null,
    var endTime: Instant? = null,
    var dayOfWeek: String? = null,
    var coefficient: Float? = null,
    var coefficientOt: Float? = null,
    var isOvernight: Boolean? = false,
    var startBreakTime: Instant? = null,
    var endBreakTime: Instant? = null,
)

data class UpdateOperationalDTO(
    var id: Long? = null,
    var title: String? = null,
    var startTime: Instant? = null,
    var endTime: Instant? = null,
    var dayOfWeek: String? = null,
    var coefficient: Float? = null,
    var coefficientOt: Float? = null,
    var isOvernight: Boolean? = false,
    var startBreakTime: Instant? = null,
    var endBreakTime: Instant? = null,
)


data class OperationalDTO(
    var id: Long? = null,
    var title: String? = null,
    var startTime: Instant? = null,
    var endTime: Instant? = null,
    var dayOfWeek: String? = null,
    var coefficient: Float? = null,
    var coefficientOt: Float? = null,
    var isOvernight: Boolean? = false,
    var startBreakTime: Instant? = null,
    var endBreakTime: Instant? = null,
    var operationalGroupId: Long? = null,
)