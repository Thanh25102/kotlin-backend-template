package com.khipster.template.khipstertemplate.modules.attendance.holiday

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.khipster.template.khipstertemplate.config.LocalDateToInstantDeserializer
import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant

data class HolidayDTO(
    var id: Long? = null,
    var title: String? = null, // chua co validate title not null
    var description: String? = null,
    var startDate: Instant? = null, // chua co validate start < end
    var endDate: Instant? = null, // chua co validate end > start
    var coefficient: Float? = null, // chua co validate coefficient > 0
)

data class CreateHolidayDTO(
    var title: String? = null, // chua co validate title not null
    var description: String? = null,
    @JsonDeserialize(using = LocalDateToInstantDeserializer::class)
    @Schema(type = "string", format = "date", example = "2023-12-31")
    var startDate: Instant? = null, // chua co validate start < end
    @JsonDeserialize(using = LocalDateToInstantDeserializer::class)
    @Schema(type = "string", format = "date", example = "2023-12-31")
    var endDate: Instant? = null, // chua co validate end > start
    var coefficient: Float? = null, // chua co validate coefficient > 0
)

data class UpdateHolidayDTO(
    var id: Long? = null,
    var title: String? = null, // chua co validate title not null
    var description: String? = null,
    @JsonDeserialize(using = LocalDateToInstantDeserializer::class)
    @Schema(type = "string", format = "date", example = "2023-12-31")
    var startDate: Instant? = null, // chua co validate start < end
    @JsonDeserialize(using = LocalDateToInstantDeserializer::class)
    @Schema(type = "string", format = "date", example = "2023-12-31")
    var endDate: Instant? = null, // chua co validate end > start
    var coefficient: Float? = null, // chua co validate coefficient > 0
)