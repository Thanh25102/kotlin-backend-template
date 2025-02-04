package com.khipster.template.khipstertemplate.modules.warning

import com.khipster.template.khipstertemplate.Gender
import com.khipster.template.khipstertemplate.Mask
import com.khipster.template.khipstertemplate.Status
import java.time.Instant

data class WarningSettingDTO(
    val id: Long? = null,
    var warningName: String? = null,
    var status: Status? = null,
    var gender: Gender? = null,
    var mask: Mask? = null,
    var createdAt: Instant? = null,
    var listId: String? = null,
    var rangeAge: String? = null,
    var warningTimesDto: MutableSet<WarningTimeDTO>? = null,
    val warningCameras: List<String>? = null
)

data class CreateWarningSetting(
    var warningName: String? = null,
    var status: Status? = null,
    var gender: Gender? = null,
    var mask: Mask? = null,
    var listId: String? = null,
    var rangeAge: String? = null,
    var warningTimesDto: MutableSet<WarningTimeDTO>? = null,
    val warningCameras: List<String>? = null
)

data class UpdateWarningSetting(
    val id: Long? = null,
    var warningName: String? = null,
    var status: Status? = null,
    var gender: Gender? = null,
    var mask: Mask? = null,
    var createdAt: Instant? = null,
    var listId: String? = null,
    var rangeAge: String? = null,
    var warningTimeEntities: MutableSet<WarningTimeDTO>? = null,
    val warningCameras: List<String>? = null
)