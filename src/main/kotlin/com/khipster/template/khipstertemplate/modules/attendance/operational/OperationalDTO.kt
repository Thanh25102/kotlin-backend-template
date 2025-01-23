package com.khipster.template.khipstertemplate.modules.attendance.operational

import java.time.Instant

data class CreateOperationalDTO(
    var title: String? = null, // chua co validate title not null
    var startTime: Instant? = null, // chua co validate start < end
    var endTime: Instant? = null, // chua co validate end > start
    var dayOfWeek: String? = null,
    var coefficient: Float? = null,// chua co validate coefficient > 0
    var coefficientOt: Float? = null, //chua co validate coefficientOt > 0
    var isOvernight: Boolean? = false,
    var startBreakTime: Instant? = null, // chua co validate startBreak < endBeak
    var endBreakTime: Instant? = null, // chua co validate endBreak > startBreak
)

data class UpdateOperationalDTO(
    var id: Long? = null,
    var title: String? = null, // chua co validate title not null
    var startTime: Instant? = null, // chua co validate start < end
    var endTime: Instant? = null,   // chua co validate end > start
    var dayOfWeek: String? = null,
    var coefficient: Float? = null, // chua co validate coefficient > 0
    var coefficientOt: Float? = null,  // chua co validate coefficientOt > 0
    var isOvernight: Boolean? = false,
    var startBreakTime: Instant? = null, // chua co validate startBreak < endBreak
    var endBreakTime: Instant? = null, // chua co validate endBreak > startBreak
)


data class OperationalDTO(
    var id: Long? = null,
    var title: String? = null,  // chua co validate title not null
    var startTime: Instant? = null, // chua co validate start < end
    var endTime: Instant? = null, // chua co validate end > start
    var dayOfWeek: String? = null,
    var coefficient: Float? = null, // chua co validate coefficient > 0
    var coefficientOt: Float? = null, // chua co validate coefficientOt > 0
    var isOvernight: Boolean? = false,
    var startBreakTime: Instant? = null, // chua co validate startBreak < endBreak
    var endBreakTime: Instant? = null, // chua co validate endBreak > startBreak
    var operationalGroupId: Long? = null,
)