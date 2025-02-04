package com.khipster.template.khipstertemplate.modules.attendance.operational

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalTime

data class CreateOperationalDTO(
    var title: String? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", pattern = "^([01]\\d|2[0-3]):([0-5]\\d)$", example = "08:30")
    var startTime: LocalTime? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", pattern = "^([01]\\d|2[0-3]):([0-5]\\d)$", example = "08:30")
    var endTime: LocalTime? = null,
    var dayOfWeek: String? = null,
    var coefficient: Float? = null,// chua co validate coefficient > 0
    var coefficientOt: Float? = null, //chua co validate coefficientOt > 0
    var isOvernight: Boolean? = false,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", pattern = "^([01]\\d|2[0-3]):([0-5]\\d)$", example = "08:30")
    var startBreakTime: LocalTime? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", pattern = "^([01]\\d|2[0-3]):([0-5]\\d)$", example = "08:30")
    var endBreakTime: LocalTime? = null,
)

data class UpdateOperationalDTO(
    var id: Long? = null,
    var title: String? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", pattern = "^([01]\\d|2[0-3]):([0-5]\\d)$", example = "08:30")
    var startTime: LocalTime? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", pattern = "^([01]\\d|2[0-3]):([0-5]\\d)$", example = "08:30")
    var endTime: LocalTime? = null,
    var dayOfWeek: String? = null,
    var coefficient: Float? = null, // chua co validate coefficient > 0
    var coefficientOt: Float? = null,  // chua co validate coefficientOt > 0
    var isOvernight: Boolean? = false,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", pattern = "^([01]\\d|2[0-3]):([0-5]\\d)$", example = "08:30")
    var startBreakTime: LocalTime? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", pattern = "^([01]\\d|2[0-3]):([0-5]\\d)$", example = "08:30")
    var endBreakTime: LocalTime? = null,
)


data class OperationalDTO(
    var id: Long? = null,
    var title: String? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", pattern = "^([01]\\d|2[0-3]):([0-5]\\d)$", example = "08:30")
    var startTime: LocalTime? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", pattern = "^([01]\\d|2[0-3]):([0-5]\\d)$", example = "08:30")
    var endTime: LocalTime? = null,
    var dayOfWeek: String? = null,
    var coefficient: Float? = null, // chua co validate coefficient > 0
    var coefficientOt: Float? = null, // chua co validate coefficientOt > 0
    var isOvernight: Boolean? = false,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", pattern = "^([01]\\d|2[0-3]):([0-5]\\d)$", example = "08:30")
    var startBreakTime: LocalTime? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", pattern = "^([01]\\d|2[0-3]):([0-5]\\d)$", example = "08:30")
    var endBreakTime: LocalTime? = null,
    var operationalGroupId: Long? = null,
)