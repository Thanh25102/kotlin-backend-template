package com.khipster.template.khipstertemplate.modules.warning

import com.khipster.template.khipstertemplate.Status
import java.time.Instant

data class WarningDTO(
    val id: Long? = null,
    val warningName: String? = null,
    val warningTime: Instant? = null,
    val status: Status? = null,
    val frameImage: String? = null,
    val cameraName: String? = null,
    val cameraId: Int? = null
)
