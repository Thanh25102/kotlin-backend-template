package com.khipster.template.khipstertemplate.modules.attendance.operational

import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant

data class CreateOperationalDTO(
    var title: String? = null,
    @Schema(type = "string", format = "time", example = "2023-12-31")
    var startTime: Instant? = null,
    @Schema(type = "string", format = "time", example = "2023-12-31")
    var endTime: Instant? = null,
    var dayOfWeek: String? = null,
    var coefficient: Float? = null,// chua co validate coefficient > 0
    var coefficientOt: Float? = null, //chua co validate coefficientOt > 0
    var isOvernight: Boolean? = false,
    @Schema(type = "string", format = "time", example = "2023-12-31")
    var startBreakTime: Instant? = null,
    @Schema(type = "string", format = "time", example = "2023-12-31")
    var endBreakTime: Instant? = null,
)

data class UpdateOperationalDTO(
    var id: Long? = null,
    var title: String? = null,
    @Schema(type = "string", format = "time", example = "2023-12-31")
    var startTime: Instant? = null,
    @Schema(type = "string", format = "time", example = "2023-12-31")
    var endTime: Instant? = null,
    var dayOfWeek: String? = null,
    var coefficient: Float? = null, // chua co validate coefficient > 0
    var coefficientOt: Float? = null,  // chua co validate coefficientOt > 0
    var isOvernight: Boolean? = false,
    @Schema(type = "string", format = "time", example = "2023-12-31")
    var startBreakTime: Instant? = null,
    @Schema(type = "string", format = "time", example = "2023-12-31")
    var endBreakTime: Instant? = null,
)


data class OperationalDTO(
    var id: Long? = null,
    var title: String? = null,
    @Schema(type = "string", format = "time", example = "2023-12-31")
    var startTime: Instant? = null,
    @Schema(type = "string", format = "time", example = "2023-12-31")
    var endTime: Instant? = null,
    var dayOfWeek: String? = null,
    var coefficient: Float? = null, // chua co validate coefficient > 0
    var coefficientOt: Float? = null, // chua co validate coefficientOt > 0
    var isOvernight: Boolean? = false,
    @Schema(type = "string", format = "time", example = "2023-12-31")
    var startBreakTime: Instant? = null,
    @Schema(type = "string", format = "time", example = "2023-12-31")
    var endBreakTime: Instant? = null,
    var operationalGroupId: Long? = null,
)